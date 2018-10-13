package com.crud.library.mapper;

import com.crud.library.domain.entities.BookRental;
import com.crud.library.domainDto.BookRentalDto;

import java.util.List;

public interface BookRentalMapper {

    BookRental mapToBookRental(BookRentalDto bookRentalDto);

    BookRentalDto mapToBookRentalDto(BookRental bookRental);

    List<BookRentalDto> mapsToBooksRentalDTO(List<BookRental> bookRentals);


}
