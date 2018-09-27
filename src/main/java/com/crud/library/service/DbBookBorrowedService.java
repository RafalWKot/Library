package com.crud.library.service;

import com.crud.library.domain.dao.BookBorrowed;

import java.util.List;

public interface DbBookBorrowedService {

    List<BookBorrowed> getBookBorrowed();

    BookBorrowed getBookBorrowed(Long id);

    BookBorrowed borrowBook(BookBorrowed bookBorrowed, int weeksBorrowQuantity);

    void returnBook(BookBorrowed bookBorrowed);

    void deleteBookBorrowed(Long id);

    void updateBookBorrowed(BookBorrowed bookBorrowed);
}
