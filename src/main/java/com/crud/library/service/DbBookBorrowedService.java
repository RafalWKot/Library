package com.crud.library.service;

import com.crud.library.domain.RequestBookBorrowed;
import com.crud.library.domain.entities.BookBorrowed;

import java.util.List;

public interface DbBookBorrowedService {

    List<BookBorrowed> getBookBorrowed();

    BookBorrowed getBookBorrowed(Long id);

    BookBorrowed borrowBook(BookBorrowed bookBorrowed);

    void deleteBookBorrowed(Long id);

    void updateBookBorrowed(BookBorrowed bookBorrowed, RequestBookBorrowed requestBookBorrowed);
}
