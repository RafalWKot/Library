package com.crud.library.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
//@RequiredArgsConstructor
public class UserDto {
    final private Long id;
    final private String firstname;
    final private String lastname;
    final private String pesel;
    private String username;
    private String password;
    final private Timestamp registrationDate;

    public void setPassword(String password) {
        this.password = password;
    }
}
