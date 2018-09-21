package com.crud.library.domainDTO;

import com.crud.library.domain.BookCopy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDTO {
    private  Long id;
    private  String title;
    private  String author;
    private  String pubYear;
}
