package com.crud.library.domain;

public enum BookCopyStatus {

    Free("Dostepna"),
    Booked("Zarezerwowana");


    private String text;

    BookCopyStatus(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
