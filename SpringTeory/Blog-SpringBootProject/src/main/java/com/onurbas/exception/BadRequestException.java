package com.onurbas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)//HTTP 400 BAD REQUEST*
public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
	super(message);
  }
}
