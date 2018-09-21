package com.crud.library.service;

import com.crud.library.domain.BookCopy;

import java.util.List;
import java.util.Optional;

public interface DbBookCopyService {

    List<BookCopy> getBookCopies();

    List<BookCopy> getBookCopiesByBookId(Long idBook);

    BookCopy getBookCopy(Long idBookCopy);

    BookCopy save(BookCopy bookCopy);

    void delete(Long id);
}
