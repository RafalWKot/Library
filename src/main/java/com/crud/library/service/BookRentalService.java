package com.crud.library.service;

import com.crud.library.domain.OperationType;
import com.crud.library.domain.entities.BookRental;

import java.util.List;

public interface BookRentalService {

    List<BookRental> getBookRental();

    BookRental getBookRental(Long id);

    BookRental borrowBook(BookRental bookRental);

    void deleteBookRental(Long id);

    void updateBookRental(BookRental bookRental, OperationType operationType);
}
