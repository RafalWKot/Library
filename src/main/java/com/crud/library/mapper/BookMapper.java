package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domainDTO.BookDTO;

import java.util.List;

public interface BookMapper {

    BookDTO mapToDto(final Book book);

    Book mapToBook(final BookDTO bookDTO);

    List<BookDTO> mapToBooksDTO(final List<Book> books);
}
