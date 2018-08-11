package com.crud.library.repository;

import com.crud.library.domain.BookLoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Long> {

    Optional<BookLoan> findById(Long id);

    @Override
    BookLoan save(BookLoan bookLoan);
}
