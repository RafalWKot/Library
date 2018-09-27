package com.crud.library.domain.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookBorrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne            //(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookCopy_id")
    private BookCopy bookCopy;

    @ManyToOne             //(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDate borrowDate;

    @Column
    private LocalDate plannedReturnDate;

    @Column
    private LocalDate returnDate;

    @Column
    private BigDecimal penaltyFee;

    public BookBorrowed(BookCopy bookCopy, User user) {
        this.bookCopy = bookCopy;
        this.user = user;
    }

    public BookBorrowed(Long id, BookCopy bookCopy, User user, LocalDate borrowDate, LocalDate plannedReturnDate, LocalDate returnDate) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.user = user;
        this.borrowDate = borrowDate;
        this.plannedReturnDate = plannedReturnDate;
        this.returnDate = returnDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setPlannedReturnDate(LocalDate plannedReturnDate) {
        this.plannedReturnDate = plannedReturnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }


}
