package com.crud.library.domainDTO;

import com.crud.library.domain.dao.BookCopy;
import com.crud.library.domain.dao.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class BookBorrowedResponseDto {

    private Long id;
    private BookCopy bookCopy;
    private User user;
    private Date borrowDate;
    private Date plannedReturnDate;
    private Date returnDate;
}
