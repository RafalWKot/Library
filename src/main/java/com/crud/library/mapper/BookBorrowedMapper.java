package com.crud.library.mapper;

import com.crud.library.domain.BookBorrowed;
import com.crud.library.domainDTO.BookBorrowedDTO;

import java.util.List;

public interface BookBorrowedMapper {

    BookBorrowed mapToBookBorrowed(BookBorrowedDTO bookBorrowedDTO);

    BookBorrowedDTO mapToBookBorrowedDto(BookBorrowed bookBorrowed);

    List<BookBorrowedDTO> mapsToBooksBorrowedDTO(List<BookBorrowed> bookBorroweds);
}
