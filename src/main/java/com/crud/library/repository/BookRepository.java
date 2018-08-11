package com.crud.library.repository;

import com.crud.library.domain.Book;
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

    Optional<Book> findByTitle(String title);

    Optional<Book> findByAuthor(String author);

    Optional<Book> findByTitleAndAuthor(String title, String author);

    @Override
    Book save(Book book);
}

