package com.crud.library.controller;

import com.crud.library.domain.BookLoan;
import com.crud.library.domainDTO.BookLoanDTO;
import com.crud.library.mapper.BookLoanMapper;
import com.crud.library.service.DbBookLoanService;
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
@RequestMapping(value = "/v1/bookLoans")
public class BookLoanController {

    @Autowired
    BookLoanMapper bookLoanMapper;

    @Autowired
    DbBookLoanService dbBookLoanService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookLoanDTO> getBookLoans() {
        return bookLoanMapper.mapsToBookLoansDTO(dbBookLoanService.getBookLoans());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookLoanDTO getBookLoan(@PathVariable("id") Long idBookLoan) {
        return bookLoanMapper.mapToBookLoanDto(dbBookLoanService.getBookLoan(idBookLoan));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewBookLoan(@RequestBody BookLoanDTO bookLoanDTO, UriComponentsBuilder uri) {
        BookLoan bookLoan = dbBookLoanService.addBookLoan(bookLoanMapper.mapToBookLoan(bookLoanDTO));
        UriComponents uriComponents = uri.path("/v1/bookLoans/{id}").buildAndExpand(bookLoan.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteBookLoan(@PathVariable("id") Long idBookLoan) {
        dbBookLoanService.delete(idBookLoan);
    }
    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateBookLoan(@RequestBody BookLoanDTO bookLoanDTO)  {
        dbBookLoanService.updateBookLoan(bookLoanMapper.mapToBookLoan(bookLoanDTO));
    }
}
