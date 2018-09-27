package com.crud.library.domainDTO;

import com.crud.library.domain.dao.BookCopy;
import com.crud.library.domain.dao.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBookBorrowedDto {
    private BookCopy bookCopy;
    private User user;
    private int weekQuantity;
}

