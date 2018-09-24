package com.crud.library.service.impl;

import com.crud.library.domain.BookCopy;
import com.crud.library.exception.BookCopyInvalidInputDataException;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BookNotFoundException;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.service.DbBookCopyService;
import com.crud.library.service.DbBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DbBookCopyServiceImpl implements DbBookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Autowired
    DbBookService dbBookService;

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
    public BookCopy save(BookCopy bookCopy) {
        if (!dbBookService.getBook(bookCopy.getBook().getId()).equals(bookCopy.getBook())) {
            throw new BookNotFoundException();
        }
        return bookCopyRepository.save(bookCopy);
    }

    @Override
    public void delete(Long idBookCopy) {
        bookCopyRepository.delete(idBookCopy);
    }

    @Override
    public void changeStatus(BookCopy bookCopy) {
        if (!(bookCopyRepository.exists(bookCopy.getId()) &&
                bookCopy.getBook().equals(dbBookService.getBook(bookCopy.getBook().getId())))) {
            throw new BookCopyInvalidInputDataException();
        }
        bookCopyRepository.save(bookCopy);
    }
}
