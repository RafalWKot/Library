package com.crud.library.controller;


import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.entities.BookCopy;
import com.crud.library.domainDto.BookCopyDto;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.impl.DbBookCopyServiceImpl;
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
@RequestMapping(value = "/v1/bookCopies")
public class BookCopyController {

    private final BookCopyMapper bookCopyMapper;

    private final DbBookCopyServiceImpl dbBookCopyService;

    public BookCopyController(BookCopyMapper bookCopyMapper, DbBookCopyServiceImpl dbBookCopyService) {
        this.bookCopyMapper = bookCopyMapper;
        this.dbBookCopyService = dbBookCopyService;
    }

    @Value("${my.server.address}")
    private String serverAddress;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookCopyDto> getBookCopies() {
        return bookCopyMapper.mapToBookCopiesDto(dbBookCopyService.getBookCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idBookCopy}")
    public BookCopyDto getBookCopyById(@PathVariable(value = "idBookCopy") Long idBookCopy) {

        return bookCopyMapper.mapToBookCopyDTO(dbBookCopyService.getBookCopy(idBookCopy));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/idBook/{idBook}")
    public  List<BookCopyDto> getBookCopyByBookId(@PathVariable(value = "idBook") Long idBook) {
        return bookCopyMapper.mapToBookCopiesDto(dbBookCopyService.getBookCopiesByBookId(idBook));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/booksAvailable/{idBook}")
    public List<BookCopyDto> getBookCopyAvailableToBorrow(@PathVariable(value = "idBook") Long idBook) {
        return bookCopyMapper.mapToBookCopiesDto(dbBookCopyService.getBookCopyAvailableToBorro(idBook, BookCopyStatus.Free.text()));
    }

    @RequestMapping(method = RequestMethod.POST, consumes =  APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = dbBookCopyService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        UriComponents uriComponents = uriComponentsBuilder.path(serverAddress + "/v1/bookCopies/{id}").buildAndExpand(bookCopy.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        dbBookCopyService.updateBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBookCopy(@PathVariable(value = "id") Long idBookCopy) {
        dbBookCopyService.deleteBookCopy(idBookCopy);
    }
}
