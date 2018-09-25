package com.crud.library.service.impl;

import com.crud.library.domain.BookLoan;
import com.crud.library.exception.BookLoanInvalidInputDataException;
import com.crud.library.exception.BookLoanNotFoundException;
import com.crud.library.repository.BookLoanRepository;
import com.crud.library.service.DbBookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
public class DbBookLoanServiceImpl implements DbBookLoanService {

    @Autowired
    BookLoanRepository bookLoanRepository;

    @Override
    public List<BookLoan> getBookLoans() {
        return bookLoanRepository.findAll();
    }

    @Override
    public BookLoan getBookLoan(Long bookLoanId) {
        return bookLoanRepository.findById(bookLoanId).orElseThrow(BookLoanNotFoundException::new);
    }

    @Override
    public BookLoan addBookLoan(BookLoan bookLoan) {
        return bookLoanRepository.save(bookLoan);
    }

    @Override
    public void delete(Long id) {
        bookLoanRepository.delete(id);
    }

    @Override
    public void updateBookLoan(BookLoan bookLoan) {
        if(!bookLoanRepository.exists(bookLoan.getId())){
            throw new BookLoanNotFoundException();
        }
        if (!bookLoanRepository.findById(bookLoan.getId()).equals(bookLoan)){
            throw new BookLoanInvalidInputDataException();
        }
        bookLoanRepository.save(bookLoan);
    }
}
