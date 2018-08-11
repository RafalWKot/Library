package com.crud.library.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This resource exists.")
public class UserDuplicateException extends RuntimeException{
}
