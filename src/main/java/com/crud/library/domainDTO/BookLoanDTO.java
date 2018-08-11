package com.crud.library.domainDTO;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookLoanDTO {

    private Long id;
    private BookCopy bookCopy;
    private User user;
}
