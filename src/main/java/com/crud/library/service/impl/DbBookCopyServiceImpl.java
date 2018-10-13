package com.crud.library.service.impl;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.entities.BookCopy;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.service.DbBookCopyService;
import com.crud.library.service.DbBookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DbBookCopyServiceImpl implements DbBookCopyService {

    private final BookCopyRepository bookCopyRepository;

    private final DbBookService dbBookService;

    public DbBookCopyServiceImpl(BookCopyRepository bookCopyRepository, DbBookService dbBookService) {
        this.bookCopyRepository = bookCopyRepository;
        this.dbBookService = dbBookService;
    }

    @Override
    public List<BookCopy> getBookCopies() {
        return bookCopyRepository.findAll();
    }

    @Override
    public BookCopy getBookCopy(Long idBookCopy) {
        return bookCopyRepository.findById(idBookCopy).orElseThrow(BookCopyNotFoundException::new);
    }

    @Override
    public List<BookCopy> getBookCopiesByBookId(Long idBook) {
        return bookCopyRepository.findByBook_Id(idBook);
    }

    @Override
    public List<BookCopy> getBookCopyAvailableToBorrow(Long idBook, BookCopyStatus bookCopyStatus) {
        return bookCopyRepository.findBookCopyByBook_IdAndStatusIsLike(idBook, bookCopyStatus.text());
    }

    @Override
    public BookCopy saveBookCopy(BookCopy bookCopy) {
        if (!dbBookService.getBook(bookCopy.getBook().getId()).equals(bookCopy.getBook())) {
            throw new BookNotFoundException();
        }
        return bookCopyRepository.save(bookCopy);
    }

    @Override
    public void deleteBookCopy(Long idBookCopy) {
        Optional.ofNullable(bookCopyRepository.findById(idBookCopy)).orElseThrow(BookCopyNotFoundException::new);
        bookCopyRepository.delete(idBookCopy);
    }

    @Override
    public void updateBookCopy(BookCopy bookCopy) {
        Optional.ofNullable(bookCopyRepository.findById(bookCopy.getId())).orElseThrow(BookCopyNotFoundException::new);
        bookCopyRepository.save(bookCopy);
    }

}
