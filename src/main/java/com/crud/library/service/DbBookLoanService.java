package com.crud.library.service;

import com.crud.library.domain.BookLoan;

import java.util.Optional;

public interface DbBookLoanService {

    Optional<BookLoan> getBookLoan(Long id);

    BookLoan addBoakLoan(BookLoan bookLoan);
}
