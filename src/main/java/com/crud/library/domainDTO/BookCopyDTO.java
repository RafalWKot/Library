package com.crud.library.domainDTO;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookLoan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookCopyDTO {
    private Long id;
    private Book book;
    private String status;
    private List<BookLoan> bookLoans;
}
