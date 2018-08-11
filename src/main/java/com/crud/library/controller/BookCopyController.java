package com.crud.library.controller;

import com.crud.library.domainDTO.BookCopyDTO;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.impl.DbBookCopyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookCopy")
public class BookCopyController {


    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Autowired
    private DbBookCopyServiceImpl dbBookCopyService;

    @RequestMapping(method = RequestMethod.GET, value = "/getBookCopyById/{id}")
    public BookCopyDTO getBookCopyById(@PathVariable(value = "id") Long idBookCopy) {
       return bookCopyMapper.mapToBookCopyDTO(dbBookCopyService.getBookCopy(idBookCopy).get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(BookCopyDTO bookCopyDTO) {
        dbBookCopyService.save(bookCopyMapper.mapToBookCopy(bookCopyDTO));
    }
}
