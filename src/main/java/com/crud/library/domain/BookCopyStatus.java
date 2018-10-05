package com.crud.library.domain;

public enum BookCopyStatus {

    Free("Dostepna"),
    Borrowed("Wypozyczona"),
    Booked("Zarezerwowana"),
    Unavailable("Niedostępna"),
    Returned("Zwrocona");

    private String text;

    BookCopyStatus(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
