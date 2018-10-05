package com.crud.library.domainDTO;

import com.crud.library.domain.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDTO {
    private Long id;
    private Book book;
    private String status;
}
