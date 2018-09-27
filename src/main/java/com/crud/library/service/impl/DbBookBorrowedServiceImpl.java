package com.crud.library.service.impl;

import com.crud.library.domain.BookCopyStatus;
import com.crud.library.domain.HoldingTime;
import com.crud.library.domain.dao.BookBorrowed;
import com.crud.library.exception.BookBorrowedInvalidInputDataException;
import com.crud.library.exception.BookBorrowedNotFoundException;
import com.crud.library.exception.BorrowBookNotAvailableException;
import com.crud.library.repository.BookBorrowedRepository;
import com.crud.library.service.DbBookBorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Transactional
@Service
public class DbBookBorrowedServiceImpl implements DbBookBorrowedService {

    @Autowired
    BookBorrowedRepository bookBorrowedRepository;

    @Override
    public List<BookBorrowed> getBookBorrowed() {
        return bookBorrowedRepository.findAll();
    }

    @Override
    public BookBorrowed getBookBorrowed(Long bookLoanId) {
        return bookBorrowedRepository.findById(bookLoanId).orElseThrow(BookBorrowedNotFoundException::new);
    }

    @Override
    public BookBorrowed borrowBook(BookBorrowed bookBorrowed, int weeksBorrowQuantity) {
        if (bookBorrowed.getBookCopy().getStatus() == BookCopyStatus.Free.text()) {
            bookBorrowed.setBorrowDate(LocalDate.now());
            bookBorrowed.setPlannedReturnDate(LocalDate.now().plusDays(HoldingTime.addTime(weeksBorrowQuantity)));
        } else {
            throw new BorrowBookNotAvailableException();
        }
        return bookBorrowedRepository.save(bookBorrowed);
    }

    @Override
    public void returnBook(BookBorrowed bookBorrowed) {
        if (!bookBorrowedRepository.exists(bookBorrowed.getId())) {
            throw new BookBorrowedNotFoundException();
        }
        if (bookBorrowedRepository.findById(bookBorrowed.getId()).get().getReturnDate() != null ||
                bookBorrowedRepository.findById(bookBorrowed.getId()).get().getBookCopy().getStatus() != BookCopyStatus.Borrowed.text()) {
            throw new BookBorrowedInvalidInputDataException();
        }
        bookBorrowed.setReturnDate(LocalDate.now());
        bookBorrowed.getBookCopy().setStatus(BookCopyStatus.Free.text());

        bookBorrowed.setPenaltyFee(
                BigDecimal
                        .valueOf(Period
                                .between(bookBorrowed.getReturnDate(), bookBorrowed.getBorrowDate())
                                .getDays())
                        .movePointLeft(2)
                        .multiply((HoldingTime.PENALTYFEE)));
    }

    @Override
    public void deleteBookBorrowed(Long id) {
        bookBorrowedRepository.delete(id);
    }

    @Override
    public void updateBookBorrowed(BookBorrowed bookBorrowed) {
        if (!bookBorrowedRepository.exists(bookBorrowed.getId())) {
            throw new BookBorrowedNotFoundException();
        }
        if (!bookBorrowedRepository.findById(bookBorrowed.getId()).equals(bookBorrowed)) {
            throw new BookBorrowedInvalidInputDataException();
        }
        bookBorrowedRepository.save(bookBorrowed);
    }
}
