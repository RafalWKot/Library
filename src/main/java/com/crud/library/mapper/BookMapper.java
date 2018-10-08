package com.crud.library.mapper;

import com.crud.library.domain.entities.Book;
import com.crud.library.domainDto.BookDto;

import java.util.List;

public interface BookMapper {

    BookDto mapToBookDTO(final Book book);

    Book mapToBook(final BookDto bookDto);

    List<BookDto> mapToBooksDTO(final List<Book> books);
}
