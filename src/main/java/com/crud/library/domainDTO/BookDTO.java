package com.crud.library.domainDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BookDTO {
    private  Long id;
    private  String title;
    private  String author;
    private  String pubYear;
}
