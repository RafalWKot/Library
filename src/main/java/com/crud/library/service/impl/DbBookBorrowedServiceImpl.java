package com.crud.library.service.impl;

import com.crud.library.domain.BookBorrowed;
import com.crud.library.exception.BookBorrowedInvalidInputDataException;
import com.crud.library.exception.BookBorrowedNotFoundException;
import com.crud.library.repository.BookBorrowedRepository;
import com.crud.library.service.DbBookBorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
public class DbBookBorrowedServiceImpl implements DbBookBorrowedService {

    @Autowired
    BookBorrowedRepository bookBorrowedRepository;

    @Override
    public List<BookBorrowed> getBookBorrowed() {
        return bookBorrowedRepository.findAll();
    }

    @Override
    public BookBorrowed getBookBorrowed(Long bookLoanId) {
        return bookBorrowedRepository.findById(bookLoanId).orElseThrow(BookBorrowedNotFoundException::new);
    }

    @Override
    public BookBorrowed addBookBorrowed(BookBorrowed bookBorrowed) {
        return bookBorrowedRepository.save(bookBorrowed);
    }

    @Override
    public void deleteBookBorrowed(Long id) {
        bookBorrowedRepository.delete(id);
    }

    @Override
    public void updateBookBorrowed(BookBorrowed bookBorrowed) {
        if(!bookBorrowedRepository.exists(bookBorrowed.getId())){
            throw new BookBorrowedNotFoundException();
        }
        if (!bookBorrowedRepository.findById(bookBorrowed.getId()).equals(bookBorrowed)){
            throw new BookBorrowedInvalidInputDataException();
        }
        bookBorrowedRepository.save(bookBorrowed);
    }
}
