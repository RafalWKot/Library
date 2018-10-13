package com.crud.library.mapper.impl;

import com.crud.library.domain.LocalDateTimeConverter;
import com.crud.library.domain.entities.BookRental;
import com.crud.library.domainDto.BookRentalDto;
import com.crud.library.mapper.BookRentalMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookRentalMapperImpl implements BookRentalMapper {

   private final LocalDateTimeConverter localDateTimeConverter;

    public BookRentalMapperImpl(LocalDateTimeConverter localDateTimeConverter) {
        this.localDateTimeConverter = localDateTimeConverter;
    }

    @Override
    public BookRental mapToBookRental(BookRentalDto bookRentalDto) {
        return new BookRental(
                bookRentalDto.getId(),
                bookRentalDto.getBookCopy(),
                bookRentalDto.getUser(),
                localDateTimeConverter.convertToEntityAttribute(bookRentalDto.getBorrowDate()),
                localDateTimeConverter.convertToEntityAttribute(bookRentalDto.getPlannedReturnDate()),
                localDateTimeConverter.convertToEntityAttribute(bookRentalDto.getReturnDate())
        );
    }

    @Override
    public BookRentalDto mapToBookRentalDto(BookRental bookRental) {
        return new BookRentalDto(
                bookRental.getId(),
                bookRental.getBookCopy(),
                bookRental.getUser(),
                localDateTimeConverter.convertToDatabaseColumn(bookRental.getBorrowDate()),
                localDateTimeConverter.convertToDatabaseColumn(bookRental.getPlannedReturnDate()),
                localDateTimeConverter.convertToDatabaseColumn(bookRental.getReturnDate())

        );
    }

    @Override
    public List<BookRentalDto> mapsToBooksRentalDTO(List<BookRental> bookRentals) {
        return bookRentals.stream()
                .map(bookRental -> new BookRentalDto(
                        bookRental.getId(),
                        bookRental.getBookCopy(),
                        bookRental.getUser(),
                        localDateTimeConverter.convertToDatabaseColumn(bookRental.getBorrowDate()),
                        localDateTimeConverter.convertToDatabaseColumn(bookRental.getPlannedReturnDate()),
                        localDateTimeConverter.convertToDatabaseColumn(bookRental.getReturnDate())))
                .collect(Collectors.toList());
    }
}
