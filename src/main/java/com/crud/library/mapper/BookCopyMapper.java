package com.crud.library.mapper;

import com.crud.library.domain.entities.BookCopy;
import com.crud.library.domainDto.BookCopyDto;

import java.util.List;

public interface BookCopyMapper {

    BookCopyDto mapToBookCopyDTO(final BookCopy bookCopy);

    BookCopy mapToBookCopy(final BookCopyDto bookCopyDto);

    List<BookCopyDto> mapToBookCopiesDto(final List<BookCopy> bookCopies);
}
