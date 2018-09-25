package com.crud.library.mapper.impl;

import com.crud.library.domain.BookLoan;
import com.crud.library.domainDTO.BookLoanDTO;
import com.crud.library.mapper.BookLoanMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class BookLoanMapperImpl implements BookLoanMapper {

    @Override
    public BookLoan mapToBookLoan(BookLoanDTO bookLoanDTO) {
        return new BookLoan(
                bookLoanDTO.getId(),
                bookLoanDTO.getBookCopy(),
                bookLoanDTO.getUser()
        );
    }

    @Override
    public BookLoanDTO mapToBookLoanDto(BookLoan bookLoan) {
        return new BookLoanDTO(
                bookLoan.getId(),
                bookLoan.getBookCopy(),
                bookLoan.getUser()
        );
    }

    @Override
    public List<BookLoanDTO> mapsToBookLoansDTO(List<BookLoan> bookLoans) {
        return bookLoans.stream()
                .map(bookLoan -> new BookLoanDTO(
                        bookLoan.getId(),
                        bookLoan.getBookCopy(),
                        bookLoan.getUser()))
                .collect(Collectors.toList());
    }
}
