package com.crud.library.controller;


import com.crud.library.domain.BookCopy;
import com.crud.library.domainDTO.BookCopyDTO;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.impl.DbBookCopyServiceImpl;
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
@RequestMapping(value = "/v1/bookCopies")
public class BookCopyController {


    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Autowired
    private DbBookCopyServiceImpl dbBookCopyService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookCopyDTO> getBookCopies() {
        return bookCopyMapper.mapToBookCopiesDto(dbBookCopyService.getBookCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookCopyDTO getBookCopyById(@PathVariable(value = "id") Long idBookCopy) {

        return bookCopyMapper.mapToBookCopyDTO(dbBookCopyService.getBookCopy(idBookCopy));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/idBook/{idBook}")
    public  List<BookCopyDTO> getBookCopyByBookId(@PathVariable(value = "idBook") Long idBook) {
        return bookCopyMapper.mapToBookCopiesDto(dbBookCopyService.getBookCopiesByBookId(idBook));
    }

    @RequestMapping(method = RequestMethod.POST, consumes =  APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewBookCopy(@RequestBody BookCopyDTO bookCopyDTO, UriComponentsBuilder uriComponentsBuilder) {
        BookCopy bookCopy = dbBookCopyService.save(bookCopyMapper.mapToBookCopy(bookCopyDTO));
        UriComponents uriComponents = uriComponentsBuilder.path("/v1/bookCopies/{id}").buildAndExpand(bookCopy.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateBookCopy(@RequestBody BookCopyDTO bookCopyDTO) {
        dbBookCopyService.updateBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDTO));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, value = "/changeBookCopyStatus")
    public void changeBookCopyStatus(@RequestBody BookCopyDTO bookCopyDTO) {
        dbBookCopyService.changeStatus(bookCopyMapper.mapToBookCopy(bookCopyDTO));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBookCopy(@PathVariable(value = "id") Long idBookCopy) {
        dbBookCopyService.deleteBookCopy(idBookCopy);
    }
}
