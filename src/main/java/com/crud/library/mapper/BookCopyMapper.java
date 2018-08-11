package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domainDTO.BookCopyDTO;

public interface BookCopyMapper {

    BookCopyDTO mapToBookCopyDTO(BookCopy bookCopy);

    BookCopy mapToBookCopy(BookCopyDTO bookCopyDTO);
}
