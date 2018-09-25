package com.crud.library.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne            //(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookCopy_id")
    private BookCopy bookCopy;

    @ManyToOne             //(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
