package com.crud.library.repository;

import com.crud.library.domain.BookLoan;
import org.springframework.data.repository.CrudRepository;

public interface BookLoanRepository extends CrudRepository<BookLoan, Long> {
}
