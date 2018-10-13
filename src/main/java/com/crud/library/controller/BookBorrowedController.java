package com.crud.library.controller;

import com.crud.library.domain.entities.BookBorrowed;
import com.crud.library.domainDto.BookBorrowedDto;
import com.crud.library.mapper.BookBorrowedMapper;
import com.crud.library.service.DbBookBorrowedService;
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
public class BookBorrowedController {

    private final BookBorrowedMapper bookBorrowedMapper;

    private final DbBookBorrowedService dbBookBorrowedService;

    public BookBorrowedController(BookBorrowedMapper bookBorrowedMapper, DbBookBorrowedService dbBookBorrowedService) {
        this.bookBorrowedMapper = bookBorrowedMapper;
        this.dbBookBorrowedService = dbBookBorrowedService;
    }

    @Value("${my.server.address}")
    private String serverAddress;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookBorrowedDto> getBooksBorrowed() {
        return bookBorrowedMapper.mapsToBooksBorrowedDTO(dbBookBorrowedService.getBookBorrowed());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookBorrowedDto getBooksBorrowed(@PathVariable("id") Long idBookBorrowed) {
        return bookBorrowedMapper.mapToBookBorrowedDto(dbBookBorrowedService.getBookBorrowed(idBookBorrowed));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<?> addBookBorrowed(@RequestBody BookBorrowedDto bookBorrowedDto) {
            BookBorrowed bookBorrowed = dbBookBorrowedService.borrowBook(bookBorrowedMapper.mapToBookBorrowed(bookBorrowedDto));
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
            UriComponents uriComponents = uriComponentsBuilder.path(serverAddress + "/v1/bookBorrowed/{id}").buildAndExpand(bookBorrowed.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateBookBorrowed(@RequestBody BookBorrowedDto bookBorrowedDto)  {
        dbBookBorrowedService.updateBookBorrowed(bookBorrowedMapper.mapToBookBorrowed(bookBorrowedDto), bookBorrowedDto.getRequestBookBorrowed());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBookBorrowed(@PathVariable("id") Long idBookBorrowed) {
        dbBookBorrowedService.deleteBookBorrowed(idBookBorrowed);
    }

}
