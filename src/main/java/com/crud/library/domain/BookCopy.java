package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne    // sprawdzić dlaczego nie może być(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    @NotNull
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
}
