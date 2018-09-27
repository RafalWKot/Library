package com.crud.library.mapper.impl;

import com.crud.library.domain.dao.BookBorrowed;
import com.crud.library.domainDTO.BookBorrowedResponseDto;
import com.crud.library.domainDTO.CreateBookBorrowedDto;
import com.crud.library.domainDTO.UpdateBookBorrowed;
import com.crud.library.mapper.BookBorrowedMapper;
import com.crud.library.mapper.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookBorrowedMapperImpl implements BookBorrowedMapper {

    @Override
    public BookBorrowed mapToBookBorrowed(CreateBookBorrowedDto createBookBorrowedDto) {
        return new BookBorrowed(
                createBookBorrowedDto.getBookCopy(),
                createBookBorrowedDto.getUser()
        );
    }

    @Override
    public BookBorrowed mapToBookBorrowed(UpdateBookBorrowed updateBookBorrowed) {
        return new BookBorrowed(
                updateBookBorrowed.getId(),
                updateBookBorrowed.getBookCopy(),
                updateBookBorrowed.getUser(),
                DateUtils.asLocalDate(updateBookBorrowed.getBorrowDate()),
                DateUtils.asLocalDate(updateBookBorrowed.getPlannedReturnDate()),
                DateUtils.asLocalDate(updateBookBorrowed.getReturnDate())
        );
    }

    @Override
    public BookBorrowedResponseDto mapToBookBorrowedDto(BookBorrowed bookBorrowed) {
        return new BookBorrowedResponseDto(
                bookBorrowed.getId(),
                bookBorrowed.getBookCopy(),
                bookBorrowed.getUser(),
                DateUtils.asDate(bookBorrowed.getBorrowDate()),
                DateUtils.asDate(bookBorrowed.getPlannedReturnDate()),
                DateUtils.asDate(bookBorrowed.getReturnDate())
        );
    }

    @Override
    public List<BookBorrowedResponseDto> mapsToBooksBorrowedDTO(List<BookBorrowed> bookBorroweds) {
        return bookBorroweds.stream()
                .map(bookBorrowed -> new BookBorrowedResponseDto(
                        bookBorrowed.getId(),
                        bookBorrowed.getBookCopy(),
                        bookBorrowed.getUser(),
                        DateUtils.asDate(bookBorrowed.getBorrowDate()),
                        DateUtils.asDate(bookBorrowed.getPlannedReturnDate()),
                        DateUtils.asDate(bookBorrowed.getReturnDate())))
                .collect(Collectors.toList());
    }
}
