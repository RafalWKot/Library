package com.crud.library.mapper.impl;

import com.crud.library.domain.entities.Book;
import com.crud.library.domainDto.BookDto;
import com.crud.library.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto mapToBookDTO(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPubYear()
        );
    }

    @Override
    public Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPubYear()
        );
    }

    @Override
    public List<BookDto> mapToBooksDTO(List<Book> books) {
        return books.stream()
                .map(t -> new BookDto(
                        t.getId(),
                        t.getTitle(),
                        t.getAuthor(),
                        t.getPubYear()))
                .collect(Collectors.toList());
    }
}
