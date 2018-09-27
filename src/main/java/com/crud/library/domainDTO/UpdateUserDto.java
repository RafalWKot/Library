package com.crud.library.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
}
