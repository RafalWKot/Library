package com.crud.library.service;

import com.crud.library.domain.BookLoan;

import java.util.List;
import java.util.Optional;

public interface DbBookLoanService {

    List<BookLoan> getBookLoans();

    BookLoan getBookLoan(Long id);

    BookLoan addBookLoan(BookLoan bookLoan);

    void delete(Long id);

    void updateBookLoan(BookLoan bookLoan);
}
