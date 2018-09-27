package com.crud.library.domain;

public enum BookCopyStatus {

    Free("wolna"),
    Borrowed("wypożyczona");

    private String text;

    BookCopyStatus(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
