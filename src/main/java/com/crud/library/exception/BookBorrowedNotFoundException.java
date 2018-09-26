package com.crud.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This BookLoand not found in library.")
public class BookBorrowedNotFoundException extends RuntimeException {
}
