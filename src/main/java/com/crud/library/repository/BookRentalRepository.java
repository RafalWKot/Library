package com.crud.library.repository;

import com.crud.library.domain.entities.BookRental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRentalRepository extends CrudRepository<BookRental, Long> {

    @Override
    List<BookRental> findAll();

    Optional<BookRental> findById(Long id);

    @SuppressWarnings("ALL")
    @Override
    BookRental save(BookRental bookRental);

    @Override
    void delete(Long aLong);

    @Override
    boolean exists(Long aLong);

}
