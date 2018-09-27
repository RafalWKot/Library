package com.crud.library.service;

import com.crud.library.domain.dao.Book;

import java.util.List;

public interface DbBookService {

    List<Book> getBooks();

    Book getBook(Long bookId);

    Book saveBook(Book book);

    void deleteBook(Long bookId);

    void updateBook(Book book);

    List<Book> getSearchedBook(Book searchBook);
}
