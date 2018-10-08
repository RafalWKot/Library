package com.crud.library.repository;

import com.crud.library.domain.entities.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    List<BookCopy> findByBook_Id(Long bookId);

    Optional<BookCopy> findById(Long bookCopyId);

    List<BookCopy> findBookCopyByBook_IdAndStatusIsLike(Long idBook, String bookCopyStatus);

    @Override
    BookCopy save(BookCopy bookCopy);

    @Override
    void delete(Long aLong);

    @Override
    boolean exists(Long aLong);


}
