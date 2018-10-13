package com.crud.library.controller;

import com.crud.library.domain.entities.BookRental;
import com.crud.library.domainDto.BookRentalDto;
import com.crud.library.mapper.BookRentalMapper;
import com.crud.library.service.BookRentalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/booksBorrowed")
public class BookRentalController {

    private final BookRentalMapper bookRentalMapper;

    private final BookRentalService bookRentalService;

    public BookRentalController(BookRentalMapper bookRentalMapper, BookRentalService bookRentalService) {
        this.bookRentalMapper = bookRentalMapper;
        this.bookRentalService = bookRentalService;
    }

    @Value("${my.server.address}")
    private String serverAddress;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookRentalDto> getBookRentals() {
        return bookRentalMapper.mapsToBooksRentalDTO(bookRentalService.getBookRental());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookRentalDto getBookRental(@PathVariable("id") Long idBookRental) {
        return bookRentalMapper.mapToBookRentalDto(bookRentalService.getBookRental(idBookRental));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<?> addBookRental(@RequestBody BookRentalDto bookRentalDto) {
            BookRental bookRental = bookRentalService.borrowBook(bookRentalMapper.mapToBookRental(bookRentalDto));
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
            UriComponents uriComponents = uriComponentsBuilder.path(serverAddress + "/v1/bookRental/{id}").buildAndExpand(bookRental.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateBookRental(@RequestBody BookRentalDto bookRentalDto)  {
        bookRentalService.updateBookRental(bookRentalMapper.mapToBookRental(bookRentalDto), bookRentalDto.getOperationType());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBookRental(@PathVariable("id") Long idBookRental) {
        bookRentalService.deleteBookRental(idBookRental);
    }

}
