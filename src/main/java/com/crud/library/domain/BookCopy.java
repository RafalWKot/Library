package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookCopy {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    @NotNull
    private String status;

    @OneToMany( //pozbyć
            targetEntity = BookLoan.class,
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<BookLoan> bookLoans = new ArrayList<>();

    public BookCopy(Book book, String status) {
        this.book = book;
        this.status = status;
    }

}
