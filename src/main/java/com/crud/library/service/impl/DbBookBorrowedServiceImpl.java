package com.crud.library.service.impl;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.Request;
import com.crud.library.domain.entities.BookBorrowed;
import com.crud.library.exception.BookBorrowedInvalidInputDataException;
import com.crud.library.exception.BookBorrowedNotFoundException;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BorrowBookNotAvailableException;
import com.crud.library.repository.BookBorrowedRepository;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.service.DbBookBorrowedService;
import com.crud.library.service.PenaltyFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class DbBookBorrowedServiceImpl implements DbBookBorrowedService {

    @Autowired
    BookBorrowedRepository bookBorrowedRepository;

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Autowired
    PenaltyFee penaltyFee;

    @Override
    public List<BookBorrowed> getBookBorrowed() {
        return bookBorrowedRepository.findAll();
    }

    @Override
    public BookBorrowed getBookBorrowed(Long bookLoanId) {
        return bookBorrowedRepository.findById(bookLoanId).orElseThrow(BookBorrowedNotFoundException::new);
    }

    @Override
    public BookBorrowed borrowBook(BookBorrowed bookBorrowed) {
        if (bookBorrowed.getBookCopy().getStatus().equals(BookCopyStatus.Free.text())) {
            bookBorrowed.setBorrowDate(LocalDateTime.now());
            bookBorrowed.setReturnDate(null);
        } else {
            throw new BorrowBookNotAvailableException();
        }
        return bookBorrowedRepository.save(bookBorrowed);
    }

//    @Override
//    public void returnBook(BookBorrowed bookBorrowed) {
//        if (!bookBorrowedRepository.exists(bookBorrowed.getId())) {
//            throw new BookBorrowedNotFoundException();
//        }
//        if (bookBorrowedRepository.findById(bookBorrowed.getId()).get().getReturnDate() != null ||
//                bookBorrowedRepository.findById(bookBorrowed.getId()).get().getBookCopy().getStatus() != BookCopyStatus.Borrowed.text()) {
//            throw new BookBorrowedInvalidInputDataException();
//        }
//

    @Override
    public void deleteBookBorrowed(Long id) {
        bookBorrowedRepository.delete(id);
    }

    @Override
    public void updateBookBorrowed(BookBorrowed bookBorrowed, Request request) {
        BookBorrowed bookBorrowedFromDb = bookBorrowedRepository.findById(bookBorrowed.getId()).orElseThrow(BookBorrowedNotFoundException::new);

        if (!bookBorrowed.equals(bookBorrowedFromDb)) {
            throw new BookBorrowedInvalidInputDataException();
        }

        if (request == Request.Return) {
            returnBook(bookBorrowed);
        } else if (request == Request.Renew) {
            renewBook(bookBorrowed);
        } else {
            bookBorrowedRepository.save(bookBorrowed);
        }
    }

    private boolean renewBook(BookBorrowed bookBorrowed) {
        if (bookCopyRepository.findById(bookBorrowed.getBookCopy().getId()).orElseThrow(BookCopyNotFoundException::new)
                .getStatus().equals(BookCopyStatus.Booked)) {
            return false;
        } else {
            bookBorrowedRepository.save(bookBorrowed);
            return true;
        }
    }

    private void returnBook(BookBorrowed bookBorrowed) {
        BookBorrowed bookBorrowedFromDb = bookBorrowedRepository.findById(bookBorrowed.getId())
                .orElseThrow(BookBorrowedNotFoundException::new);

        if (!bookBorrowed.equals(bookBorrowedFromDb)) {
            throw new BookBorrowedInvalidInputDataException();
        }

        bookBorrowed.setReturnDate(LocalDateTime.now());
        bookBorrowed.getBookCopy().setStatus(BookCopyStatus.Free.text());
        bookBorrowed.setPenaltyFee(penaltyFee.calculatePenaltyFee(
                bookBorrowed.getBorrowDate(),
                bookBorrowed.getReturnDate()
        ));
    }
}
