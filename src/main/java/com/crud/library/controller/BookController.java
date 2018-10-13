package com.crud.library.controller;

import com.crud.library.domain.entities.Book;
import com.crud.library.domainDto.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbBookService;
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
@RequestMapping(value = "/v1/books")
public class BookController {

    private final DbBookService dbBookService;

    private final BookMapper bookMapper;

    public BookController(DbBookService dbBookService, BookMapper bookMapper) {
        this.dbBookService = dbBookService;
        this.bookMapper = bookMapper;
    }

    @Value("${my.server.address}")
    private String serverAddress;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return bookMapper.mapToBooksDTO(dbBookService.getBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookDto getBookById(@PathVariable("id") Long idBook) {
        return bookMapper.mapToBookDTO(dbBookService.getBook(idBook));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<BookDto> getBookBySearch(@RequestParam(required = false, defaultValue = "%") String title,
                                         @RequestParam(required = false, defaultValue = "%") String author,
                                         @RequestParam(required = false, defaultValue = "%") String pubYear) {
        Book searchBook = new Book(title, author, pubYear);
        return bookMapper.mapToBooksDTO(dbBookService.getSearchedBook(searchBook));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewBook(@RequestBody BookDto bookDto) {
        Book book = dbBookService.saveBook(bookMapper.mapToBook(bookDto));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        UriComponents uriComponents = uriComponentsBuilder.path(serverAddress + "/v1/books/{id}").buildAndExpand(book.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateBook(@RequestBody BookDto bookDto) {
        dbBookService.updateBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBook(@PathVariable("id") Long idBook) {
        dbBookService.deleteBook(idBook);
    }
}
