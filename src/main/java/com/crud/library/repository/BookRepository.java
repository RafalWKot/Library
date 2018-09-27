package com.crud.library.repository;

import com.crud.library.domain.dao.Book;
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

    Optional<Book> findByTitleAndAuthor(String title, String author);

    List<Book> findBookByTitleLikeAndAuthorLikeAndPubYearLike(String title, String author, String pubYear);

    @Override
    Book save(Book book);

    @Override
    void delete(Long aLong);

    @Override
    boolean exists(Long aLong);
}

