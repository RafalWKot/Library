package com.crud.library.mapper;

import com.crud.library.domain.BookLoan;
import com.crud.library.domainDTO.BookLoanDTO;

public interface BookLoanMapper {

    BookLoan mapToBookLoan(BookLoanDTO bookLoanDTO);

    BookLoanDTO mapToBookLoanDto(BookLoan bookLoan);
}
