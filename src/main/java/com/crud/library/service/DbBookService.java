package com.crud.library.service;

import com.crud.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface DbBookService {

    List<Book> getBooks();

    Book getBook(Long bookId);

    List<Book> getBookByTitle(String title);

    List<Book> getBookByAuthor(String author);

    Book saveBook(Book book);

    void deleteBook(Long bookId);
}
