package com.crud.library.validation;


import org.springframework.stereotype.Service;

@Service
public class UserValidation {

    public boolean isValidPesel(String pesel) {
        int psize = pesel.length();
        if (psize != 11) {
            return false;
        }
        int[] weights = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
        int j, sum = 0, control;
        int csum = Integer.valueOf(pesel.substring(psize - 1));
        for (int i = 0; i < psize - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.valueOf(String.valueOf(c));
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
    }
}
