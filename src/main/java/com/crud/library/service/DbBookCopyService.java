package com.crud.library.service;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.entities.BookCopy;

import java.util.List;

public interface DbBookCopyService {

    List<BookCopy> getBookCopies();

    List<BookCopy> getBookCopiesByBookId(Long idBook);

    List<BookCopy> getBookCopyAvailableToBorrow(Long idBook, BookCopyStatus bookCopyStatus);

    BookCopy getBookCopy(Long idBookCopy);

    BookCopy saveBookCopy(BookCopy bookCopy);

    void deleteBookCopy(Long id);

    void updateBookCopy(BookCopy bookCopy);

}
