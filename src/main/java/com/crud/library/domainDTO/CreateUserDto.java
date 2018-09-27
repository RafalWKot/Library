package com.crud.library.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDto {
    private String firstname;
    private String lastname;
    private String pesel;                   //@można stworzyć adnotację isValidPesel np.
}
