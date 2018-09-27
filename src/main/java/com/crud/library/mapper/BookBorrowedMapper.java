package com.crud.library.mapper;

import com.crud.library.domain.dao.BookBorrowed;
import com.crud.library.domainDTO.BookBorrowedResponseDto;
import com.crud.library.domainDTO.CreateBookBorrowedDto;
import com.crud.library.domainDTO.UpdateBookBorrowed;

import java.util.List;

public interface BookBorrowedMapper {

    BookBorrowed mapToBookBorrowed(CreateBookBorrowedDto createBookBorrowedDto);

    public BookBorrowed mapToBookBorrowed(UpdateBookBorrowed updateBookBorrowed);

    BookBorrowedResponseDto mapToBookBorrowedDto(BookBorrowed bookBorrowed);

    List<BookBorrowedResponseDto> mapsToBooksBorrowedDTO(List<BookBorrowed> bookBorroweds);


}
