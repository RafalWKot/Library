package com.crud.library.mapper.impl;

import com.crud.library.domain.LocalDateTimeConverter;
import com.crud.library.domain.entities.BookBorrowed;
import com.crud.library.domainDto.BookBorrowedDto;
import com.crud.library.mapper.BookBorrowedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookBorrowedMapperImpl implements BookBorrowedMapper {


    @Autowired
    LocalDateTimeConverter localDateTimeConverter;

    @Override
    public BookBorrowed mapToBookBorrowed(BookBorrowedDto bookBorrowedDto) {
        return new BookBorrowed(
                bookBorrowedDto.getId(),
                bookBorrowedDto.getBookCopy(),
                bookBorrowedDto.getUser(),
                localDateTimeConverter.convertToEntityAttribute(bookBorrowedDto.getBorrowDate()),
                localDateTimeConverter.convertToEntityAttribute(bookBorrowedDto.getPlannedReturnDate()),
                localDateTimeConverter.convertToEntityAttribute(bookBorrowedDto.getReturnDate())
        );
    }

    @Override
    public BookBorrowedDto mapToBookBorrowedDto(BookBorrowed bookBorrowed) {
        return new BookBorrowedDto(
                bookBorrowed.getId(),
                bookBorrowed.getBookCopy(),
                bookBorrowed.getUser(),
                localDateTimeConverter.convertToDatabaseColumn(bookBorrowed.getBorrowDate()),
                localDateTimeConverter.convertToDatabaseColumn(bookBorrowed.getPlannedReturnDate()),
                localDateTimeConverter.convertToDatabaseColumn(bookBorrowed.getReturnDate())

        );
    }

    @Override
    public List<BookBorrowedDto> mapsToBooksBorrowedDTO(List<BookBorrowed> bookBorroweds) {
        return bookBorroweds.stream()
                .map(bookBorrowed -> new BookBorrowedDto(
                        bookBorrowed.getId(),
                        bookBorrowed.getBookCopy(),
                        bookBorrowed.getUser(),
                        localDateTimeConverter.convertToDatabaseColumn(bookBorrowed.getBorrowDate()),
                        localDateTimeConverter.convertToDatabaseColumn(bookBorrowed.getPlannedReturnDate()),
                        localDateTimeConverter.convertToDatabaseColumn(bookBorrowed.getReturnDate())))
                .collect(Collectors.toList());
    }
}
