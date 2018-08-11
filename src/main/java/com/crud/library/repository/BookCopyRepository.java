package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    Optional<BookCopy> findById(Long bookId);

    @Override
    BookCopy save(BookCopy bookCopy);
}
