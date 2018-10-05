package com.crud.library.mapper;

import com.crud.library.domain.entities.BookCopy;
import com.crud.library.domainDTO.BookCopyDTO;

import java.util.List;

public interface BookCopyMapper {

    BookCopyDTO mapToBookCopyDTO(final BookCopy bookCopy);

    BookCopy mapToBookCopy(final BookCopyDTO bookCopyDTO);

    List<BookCopyDTO> mapToBookCopiesDto(final List<BookCopy> bookCopies);
}
