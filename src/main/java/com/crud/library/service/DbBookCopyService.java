package com.crud.library.service;

import com.crud.library.domain.entities.BookCopy;

import java.util.List;


public interface DbBookCopyService {

    List<BookCopy> getBookCopies();

    List<BookCopy> getBookCopiesByBookId(Long idBook);

    BookCopy getBookCopy(Long idBookCopy);

    BookCopy save(BookCopy bookCopy);

    void deleteBookCopy(Long id);

    void updateBookCopy(BookCopy bookCopy);

    void changeStatus(BookCopy bookCopy);
}
