package com.crud.library.domainDto;

import com.crud.library.domain.RequestBookBorrowed;
import com.crud.library.domain.entities.BookCopy;
import com.crud.library.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class BookBorrowedDto {

    private Long id;
    private BookCopy bookCopy;
    private User user;
    private Timestamp borrowDate;
    private Timestamp plannedReturnDate;
    private Timestamp returnDate;

    private RequestBookBorrowed requestBookBorrowed;

    public BookBorrowedDto(Long id, BookCopy bookCopy, User user, Timestamp borrowDate, Timestamp plannedReturnDate, Timestamp returnDate) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.user = user;
        this.borrowDate = borrowDate;
        this.plannedReturnDate = plannedReturnDate;
        this.returnDate = returnDate;
    }
}
