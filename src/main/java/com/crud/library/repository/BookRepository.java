package com.crud.library.repository;

import com.crud.library.domain.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book>  findAll();

    Optional<Book> findById(Long bookId);

    List<Book> findBookByTitleLikeAndAuthorLikeAndPubYearLike(String title, String author, String pubYear);

    @SuppressWarnings("ALL")
    @Override
    Book save(Book book);

    @Override
    <S extends Book> Iterable<S> save(Iterable<S> entities);

    @Override
    void delete(Long aLong);

    @Override
    boolean exists(Long aLong);
}

