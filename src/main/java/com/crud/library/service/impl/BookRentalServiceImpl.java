package com.crud.library.service.impl;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.OperationType;
import com.crud.library.domain.entities.BookRental;
import com.crud.library.domain.entities.BookCopy;
import com.crud.library.exception.BookRentalInvalidInputDataException;
import com.crud.library.exception.BookRentalNotFoundException;
import com.crud.library.exception.BookRentalNotAvailableException;
import com.crud.library.repository.BookRentalRepository;
import com.crud.library.service.BookRentalService;
import com.crud.library.service.PenaltyFee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookRentalServiceImpl implements BookRentalService {

    private final BookRentalRepository bookRentalRepository;

    private final PenaltyFee penaltyFee;

    public BookRentalServiceImpl(BookRentalRepository bookRentalRepository, PenaltyFee penaltyFee) {
        this.bookRentalRepository = bookRentalRepository;
        this.penaltyFee = penaltyFee;
    }

    @Override
    public List<BookRental> getBookRental() {
        return bookRentalRepository.findAll();
    }

    @Override
    public BookRental getBookRental(Long bookLoanId) {
        return bookRentalRepository.findById(bookLoanId).orElseThrow(BookRentalNotFoundException::new);
    }

    @Override
    public BookRental borrowBook(BookRental bookRental) {
        if (bookRental.getBookCopy().getStatus().equals(BookCopyStatus.Free.text())) {
            bookRental.setBorrowDate(LocalDateTime.now());
            bookRental.setReturnDate(null);
        } else {
            throw new BookRentalNotAvailableException();
        }
        return bookRentalRepository.save(bookRental);
    }

    @Override
    public void deleteBookRental(Long idBookBorrowed) {
        Optional.ofNullable(bookRentalRepository.findById(idBookBorrowed)).orElseThrow(BookRentalNotFoundException::new);
        bookRentalRepository.delete(idBookBorrowed);
    }

    @Override
    public void updateBookRental(BookRental bookRental, OperationType operationType) {
        BookRental bookRentalFromDb = bookRentalRepository.findById(bookRental.getId()).orElseThrow(BookRentalNotFoundException::new);
        if (operationType == OperationType.Return
                && bookCopiesEquals(bookRentalFromDb.getBookCopy(), bookRental.getBookCopy())) {
            returnBook(bookRentalFromDb);
        } else if (operationType == OperationType.Renew
                && bookCopiesEquals(bookRentalFromDb.getBookCopy(), bookRental.getBookCopy())) {
            renewBook(bookRental, bookRentalFromDb);
        } else {
            bookRentalRepository.save(bookRental);
        }
    }

    private boolean renewBook(BookRental bookRental, BookRental bookRentalFromDb) {
        if (bookRentalFromDb.getBookCopy().getStatus().equals(BookCopyStatus.Booked.text())) {
            return false;
        } else {
            bookRentalRepository.save(bookRental);
            return true;
        }
    }

    private void returnBook(BookRental bookRentalFromDb) {
        bookRentalFromDb.setReturnDate(LocalDateTime.now());
        bookRentalFromDb.getBookCopy().setStatus(BookCopyStatus.Free.text());
        bookRentalFromDb.setPenaltyFee(penaltyFee.calculatePenaltyFee(
                bookRentalFromDb.getPlannedReturnDate(),
                bookRentalFromDb.getReturnDate()));
        bookRentalRepository.save(bookRentalFromDb);
    }

    boolean bookCopiesEquals(BookCopy bookCopyFromDb, BookCopy bookCopyFromDto) throws BookRentalInvalidInputDataException {
        if (bookCopyFromDto.equals(bookCopyFromDb)) {
            return true;
        } else {
            throw new BookRentalInvalidInputDataException();
        }
    }
}
