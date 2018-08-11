package com.crud.library.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class BookDTO {
    private  Long id;
    private  String title;
    private  String author;
    private  Date dateBook;
}
