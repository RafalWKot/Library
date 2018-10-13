package com.crud.library.service.impl;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.RequestBookBorrowed;
import com.crud.library.domain.entities.BookBorrowed;
import com.crud.library.domain.entities.BookCopy;
import com.crud.library.exception.BookBorrowedInvalidInputDataException;
import com.crud.library.exception.BookBorrowedNotFoundException;
import com.crud.library.exception.BorrowBookNotAvailableException;
import com.crud.library.repository.BookBorrowedRepository;
import com.crud.library.service.DbBookBorrowedService;
import com.crud.library.service.PenaltyFee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DbBookBorrowedServiceImpl implements DbBookBorrowedService {

    private final BookBorrowedRepository bookBorrowedRepository;

    private final PenaltyFee penaltyFee;

    public DbBookBorrowedServiceImpl(BookBorrowedRepository bookBorrowedRepository, PenaltyFee penaltyFee) {
        this.bookBorrowedRepository = bookBorrowedRepository;
        this.penaltyFee = penaltyFee;
    }

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

    @Override
    public void deleteBookBorrowed(Long idBookBorrowed) {
        Optional.ofNullable(bookBorrowedRepository.findById(idBookBorrowed)).orElseThrow(BookBorrowedNotFoundException::new);
        bookBorrowedRepository.delete(idBookBorrowed);
    }

    @Override
    public void updateBookBorrowed(BookBorrowed bookBorrowed, RequestBookBorrowed requestBookBorrowed) {
        BookBorrowed bookBorrowedFromDb = bookBorrowedRepository.findById(bookBorrowed.getId()).orElseThrow(BookBorrowedNotFoundException::new);
        if (requestBookBorrowed == RequestBookBorrowed.Return
                && bookCopiesEquals(bookBorrowedFromDb.getBookCopy(), bookBorrowed.getBookCopy())) {
            returnBook(bookBorrowedFromDb);
        } else if (requestBookBorrowed == RequestBookBorrowed.Renew
                && bookCopiesEquals(bookBorrowedFromDb.getBookCopy(), bookBorrowed.getBookCopy())) {
            renewBook(bookBorrowed, bookBorrowedFromDb);
        } else {
            bookBorrowedRepository.save(bookBorrowed);
        }
    }

    private boolean renewBook(BookBorrowed bookBorrowed, BookBorrowed bookBorrowedFromDb) {
        if (bookBorrowedFromDb.getBookCopy().getStatus().equals(BookCopyStatus.Booked.text())) {
            return false;
        } else {
            bookBorrowedRepository.save(bookBorrowed);
            return true;
        }
    }

    private void returnBook(BookBorrowed bookBorrowedFromDb) {
        bookBorrowedFromDb.setReturnDate(LocalDateTime.now());
        bookBorrowedFromDb.getBookCopy().setStatus(BookCopyStatus.Free.text());
        bookBorrowedFromDb.setPenaltyFee(penaltyFee.calculatePenaltyFee(
                bookBorrowedFromDb.getPlannedReturnDate(),
                bookBorrowedFromDb.getReturnDate()));
        bookBorrowedRepository.save(bookBorrowedFromDb);
    }

    boolean bookCopiesEquals(BookCopy bookCopyFromDb, BookCopy bookCopyFromDto) throws BookBorrowedInvalidInputDataException {
        if (bookCopyFromDto.equals(bookCopyFromDb)) {
            return true;
        } else {
            throw new BookBorrowedInvalidInputDataException();
        }
    }
}
