package com.crud.library.mapper;

import com.crud.library.domain.entities.BookBorrowed;
import com.crud.library.domainDTO.BookBorrowedDto;

import java.util.List;

public interface BookBorrowedMapper {

    BookBorrowed mapToBookBorrowed(BookBorrowedDto bookBorrowedDto);

    BookBorrowedDto mapToBookBorrowedDto(BookBorrowed bookBorrowed);

    List<BookBorrowedDto> mapsToBooksBorrowedDTO(List<BookBorrowed> bookBorroweds);


}
