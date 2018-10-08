package com.crud.library.mapper.impl;

import com.crud.library.domain.entities.BookCopy;
import com.crud.library.domainDto.BookCopyDto;
import com.crud.library.mapper.BookCopyMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapperImpl implements BookCopyMapper {

    @Override
    public BookCopyDto mapToBookCopyDTO(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getBook(),
                bookCopy.getStatus()
        );
    }

    @Override
    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getBook(),
                bookCopyDto.getStatus()
        );
    }

    @Override
    public List<BookCopyDto> mapToBookCopiesDto(List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(bookCopy -> new BookCopyDto(
                        bookCopy.getId(),
                        bookCopy.getBook(),
                        bookCopy.getStatus()))
                .collect(Collectors.toList());
    }
}
