package com.crud.library.controller;

import com.crud.library.domainDTO.BookCopyDTO;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.impl.DbBookCopyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/bookCopy")
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

    @RequestMapping(method = RequestMethod.POST, consumes =  APPLICATION_JSON_VALUE)
    public void save(BookCopyDTO bookCopyDTO) {
        dbBookCopyService.save(bookCopyMapper.mapToBookCopy(bookCopyDTO));
    }
}
