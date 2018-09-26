package com.crud.library.mapper.impl;

import com.crud.library.domain.BookBorrowed;
import com.crud.library.domainDTO.BookBorrowedDTO;
import com.crud.library.mapper.BookBorrowedMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookBorrowedMapperImpl implements BookBorrowedMapper {

    @Override
    public BookBorrowed mapToBookBorrowed(BookBorrowedDTO bookBorrowedDTO) {
        return new BookBorrowed(
                bookBorrowedDTO.getId(),
                bookBorrowedDTO.getBookCopy(),
                bookBorrowedDTO.getUser()
        );
    }

    @Override
    public BookBorrowedDTO mapToBookBorrowedDto(BookBorrowed bookBorrowed) {
        return new BookBorrowedDTO(
                bookBorrowed.getId(),
                bookBorrowed.getBookCopy(),
                bookBorrowed.getUser()
        );
    }

    @Override
    public List<BookBorrowedDTO> mapsToBooksBorrowedDTO(List<BookBorrowed> bookBorroweds) {
        return bookBorroweds.stream()
                .map(bookBorrowed -> new BookBorrowedDTO(
                        bookBorrowed.getId(),
                        bookBorrowed.getBookCopy(),
                        bookBorrowed.getUser()))
                .collect(Collectors.toList());
    }
}
