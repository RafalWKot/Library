package com.crud.library.domainDTO;

import com.crud.library.domain.BookLoan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
    private Date registrationDate;
    private List<BookLoan> bookLoans = new ArrayList<>();
}
