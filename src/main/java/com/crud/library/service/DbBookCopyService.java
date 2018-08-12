package com.crud.library.service;

import com.crud.library.domain.BookCopy;

import java.util.List;
import java.util.Optional;

public interface DbBookCopyService {

    List<BookCopy> getBookCopies();

    BookCopy getBookCopy(Long idBookCopy);

    BookCopy save(BookCopy bookCopy);


}
