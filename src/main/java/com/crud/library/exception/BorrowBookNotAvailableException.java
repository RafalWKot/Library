package com.crud.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Borrowing of the book is not available")
public class BorrowBookNotAvailableException extends RuntimeException {
}
