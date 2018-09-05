package com.crud.library.controller;

import com.crud.library.domainDTO.BookDTO;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/books")
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

    @RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
    public List<BookDTO> getBooksByTitle(@PathVariable("title") String title) {
        return bookMapper.mapToBooksDTO(dbBookService.getBookByTitle(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/author/{author}")
    public List<BookDTO> getBooksByAuthor(@PathVariable("author") String author) {
        return bookMapper.mapToBooksDTO(dbBookService.getBookByAuthor(author));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void addNewBook(@RequestBody BookDTO bookDTO) {
        dbBookService.saveBook(bookMapper.mapToBook(bookDTO));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public BookDTO updateBook(@RequestBody BookDTO bookDTO){
        return bookMapper.mapToBookDTO(dbBookService.saveBook(bookMapper.mapToBook(bookDTO)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBook(@PathVariable("id") Long idBook) {
        dbBookService.deleteBook(idBook);
    }
}
