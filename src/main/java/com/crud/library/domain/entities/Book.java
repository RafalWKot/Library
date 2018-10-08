package com.crud.library.domain.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String author;

    @Column
    @NotNull
    private String pubYear;

    public Book(String title, String author, String pubYear) {
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
    }
}
