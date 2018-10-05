package com.crud.library.mapper.impl;

import com.crud.library.domain.entities.BookCopy;
import com.crud.library.domainDTO.BookCopyDTO;
import com.crud.library.mapper.BookCopyMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapperImpl implements BookCopyMapper {

    @Override
    public BookCopyDTO mapToBookCopyDTO(BookCopy bookCopy) {
        return new BookCopyDTO(
                bookCopy.getId(),
                bookCopy.getBook(),
                bookCopy.getStatus()
        );
    }

    @Override
    public BookCopy mapToBookCopy(BookCopyDTO bookCopyDTO) {
        return new BookCopy(
                bookCopyDTO.getId(),
                bookCopyDTO.getBook(),
                bookCopyDTO.getStatus()
        );
    }

    @Override
    public List<BookCopyDTO> mapToBookCopiesDto(List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(bookCopy -> new BookCopyDTO(
                        bookCopy.getId(),
                        bookCopy.getBook(),
                        bookCopy.getStatus()))
                .collect(Collectors.toList());
    }
}
