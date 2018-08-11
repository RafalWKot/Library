package com.crud.library.mapper.impl;

import com.crud.library.domain.Book;
import com.crud.library.domainDTO.BookDTO;
import com.crud.library.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO mapToDto(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDateBook()
        );
    }

    @Override
    public Book mapToBook(BookDTO bookDTO) {
        return new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getDateBook()
        );
    }

    @Override
    public List<BookDTO> mapToBooksDTO(List<Book> books) {
        return books.stream()
                .map(t -> new BookDTO(
                        t.getId(),
                        t.getTitle(),
                        t.getAuthor(),
                        t.getDateBook()))
                .collect(Collectors.toList());
    }
}
