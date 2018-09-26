package com.crud.library.service;

import com.crud.library.domain.BookBorrowed;

import java.util.List;

public interface DbBookBorrowedService {

    List<BookBorrowed> getBookBorrowed();

    BookBorrowed getBookBorrowed(Long id);

    BookBorrowed addBookBorrowed(BookBorrowed bookBorrowed);

    void deleteBookBorrowed(Long id);

    void updateBookBorrowed(BookBorrowed bookBorrowed);
}
