package com.crud.library.service.impl;

import com.crud.library.domain.BookLoan;
import com.crud.library.repository.BookLoanRepository;
import com.crud.library.service.DbBookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class DbBookLoanServiceImpl implements DbBookLoanService {

    @Autowired
    BookLoanRepository bookLoanRepository;

    @Override
    public Optional<BookLoan> getBookLoan(Long bookLoanId) {
        return bookLoanRepository.findById(bookLoanId);
    }

    @Override
    public BookLoan addBoakLoan(BookLoan bookLoan) {
        return bookLoanRepository.save(bookLoan);
    }
}
