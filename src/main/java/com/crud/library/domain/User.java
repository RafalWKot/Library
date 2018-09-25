package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    @NotNull
    private String firstname;

    @Column
    @NotNull
    private String lastname;

    @Column
    @NotNull
    private String pesel;

    @Column
    private Date registrationDate;

    public User(String firstname, String lastname, String pesel) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
