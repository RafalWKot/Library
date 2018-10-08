package com.crud.library.domainDto;

import com.crud.library.domain.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private Long id;
    private Book book;
    private String status;
}
