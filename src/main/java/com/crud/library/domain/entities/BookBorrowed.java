package com.crud.library.domain.entities;


import com.crud.library.domain.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime borrowDate;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime plannedReturnDate;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime returnDate;

    @Column
    private BigDecimal penaltyFee;

    @Column
    private BigDecimal penaltyFeeRemain;

    public BookBorrowed(Long id, BookCopy bookCopy, User user, LocalDateTime borrowDate, LocalDateTime plannedReturnDate, LocalDateTime returnDate) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.user = user;
        this.borrowDate = borrowDate;
        this.plannedReturnDate = plannedReturnDate;
        this.returnDate = returnDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }


}
