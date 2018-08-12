package com.crud.library.service.impl;

import com.crud.library.domain.BookCopy;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.service.DbBookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DbBookCopyServiceImpl implements DbBookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Override
    public List<BookCopy> getBookCopies() {
        return bookCopyRepository.findAll();
    }

    @Override
    public BookCopy getBookCopy(Long idBookCopy) {
        return bookCopyRepository.findById(idBookCopy).orElseThrow(BookCopyNotFoundException::new);
    }

    @Override
    public BookCopy save(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }



}
