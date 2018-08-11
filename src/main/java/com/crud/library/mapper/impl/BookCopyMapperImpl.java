package com.crud.library.mapper.impl;

import com.crud.library.domain.BookCopy;
import com.crud.library.domainDTO.BookCopyDTO;
import com.crud.library.mapper.BookCopyMapper;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapperImpl implements BookCopyMapper {

    @Override
    public BookCopyDTO mapToBookCopyDTO(BookCopy bookCopy) {
        return new BookCopyDTO(
                bookCopy.getId(),
                bookCopy.getBook(),
                bookCopy.getStatus(),
                bookCopy.getBookLoans()
        );
    }

    @Override
    public BookCopy mapToBookCopy(BookCopyDTO bookCopyDTO) {
        return new BookCopy(
                bookCopyDTO.getId(),
                bookCopyDTO.getBook(),
                bookCopyDTO.getStatus(),
                bookCopyDTO.getBookLoans()
        );
    }
}
