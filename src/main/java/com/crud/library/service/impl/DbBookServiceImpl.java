package com.crud.library.service.impl;

import com.crud.library.domain.entities.Book;
import com.crud.library.exception.BookDuplicateException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.service.DbBookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DbBookServiceImpl implements DbBookService {

    private final BookRepository bookRepository;

    public DbBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> getSearchedBook(Book searchBook) {
        return bookRepository.findBookByTitleLikeAndAuthorLikeAndPubYearLike(searchBook.getTitle(), searchBook.getAuthor(), searchBook.getPubYear());
    }

    @Override
    public Book saveBook(Book book) {
        if(bookRepository.findBookByTitleLikeAndAuthorLikeAndPubYearLike(book.getTitle(), book.getAuthor(), book.getPubYear()).size() != 0) {
            throw new BookDuplicateException();
        }
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        Optional.ofNullable(bookRepository.findById(bookId)).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(bookId);
    }

    @Override
    public void updateBook(Book book) {
        Optional.ofNullable(bookRepository.findById(book.getId())).orElseThrow(BookNotFoundException::new);
        bookRepository.save(book);
    }
}
