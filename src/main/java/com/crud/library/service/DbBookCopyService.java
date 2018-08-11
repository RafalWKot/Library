package com.crud.library.service;

import com.crud.library.domain.BookCopy;

import java.util.Optional;

public interface DbBookCopyService {

    Optional<BookCopy> getBookCopy(Long idBookCopy);

    BookCopy save(BookCopy bookCopy);
}
