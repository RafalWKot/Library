package com.crud.library.repository;

import com.crud.library.domain.entities.BookBorrowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookBorrowedRepository extends CrudRepository<BookBorrowed, Long> {

    @Override
    List<BookBorrowed> findAll();

    Optional<BookBorrowed> findById(Long id);

    @Override
    BookBorrowed save(BookBorrowed bookBorrowed);

    @Override
    void delete(Long aLong);

    @Override
    boolean exists(Long aLong);

}
