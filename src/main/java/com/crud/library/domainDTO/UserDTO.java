package com.crud.library.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;


@Getter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;

    //@można stworzyć adnotację isValidPesel np.
    private String pesel;
    private Date registrationDate;

}
