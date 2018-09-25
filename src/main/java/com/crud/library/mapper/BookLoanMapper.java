package com.crud.library.mapper;

import com.crud.library.domain.BookLoan;
import com.crud.library.domainDTO.BookLoanDTO;

import java.util.List;

public interface BookLoanMapper {

    BookLoan mapToBookLoan(BookLoanDTO bookLoanDTO);

    BookLoanDTO mapToBookLoanDto(BookLoan bookLoan);

    List<BookLoanDTO> mapsToBookLoansDTO(List<BookLoan> bookLoans);
}
