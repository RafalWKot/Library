package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domainDTO.BookDTO;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbBookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    DbBookService dbBookService;

    @Autowired
    BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDTO> getBooks() {
        return bookMapper.mapToBooksDTO(dbBookService.getBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookDTO getBookById(@PathVariable("id") Long idBook) {
        return bookMapper.mapToBookDTO(dbBookService.getBook(idBook));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchedBooks")
    public List<BookDTO> getBookBySearch(@RequestParam(required = false, defaultValue = "%") String title,
                                                   @RequestParam(required = false, defaultValue = "%") String author,
                                                   @RequestParam(required = false, defaultValue = "%") String pubYear) {
        Book searchBook = new Book(title, author, pubYear);
        return bookMapper.mapToBooksDTO(dbBookService.getSearchedBook(searchBook));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewBook(@RequestBody BookDTO bookDTO, UriComponentsBuilder uriComponentsBuilder) {
        Book book = dbBookService.saveBook(bookMapper.mapToBook(bookDTO));
        UriComponents uriComponents = uriComponentsBuilder.path("/v1/books/{id}").buildAndExpand(book.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public BookDTO updateBook(@RequestBody BookDTO bookDTO) {
        return bookMapper.mapToBookDTO(dbBookService.saveBook(bookMapper.mapToBook(bookDTO)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBook(@PathVariable("id") Long idBook) {
        dbBookService.deleteBook(idBook);
    }
}
