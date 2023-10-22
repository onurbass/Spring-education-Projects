package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR_SERVER(5300, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4300, "Parameter Error", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4310, "User Not Found", HttpStatus.NOT_FOUND),
    MOMENT_NOT_FOUND(4311, "Moment Not Found", HttpStatus.NOT_FOUND),
    TIMELINE_NOT_FOUND(4312, "Timeline Not Found", HttpStatus.NOT_FOUND),
    UNEXPECTED_ERROR(4313, "Unexpected Error Occurred", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    HttpStatus httpStatus;
}