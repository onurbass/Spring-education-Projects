package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorMessage> handleRunTimeException(RuntimeException ex) {
	ex.printStackTrace();
	log.error(ex.toString());
	return new ResponseEntity<>(createError(ErrorType.UNEXPECTED_ERROR,ex,ex.getMessage()),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MomentException.class)
  public ResponseEntity<ErrorMessage> handleManagerException(MomentException exception) {
	ErrorType errorType = exception.getErrorType();
	HttpStatus httpStatus = errorType.getHttpStatus();
	ErrorMessage errorMessage = createError(errorType,exception);
	errorMessage.setMessage(exception.getMessage());

	return new ResponseEntity<>(errorMessage,httpStatus);
  }

  @ExceptionHandler(MomentException.class)
  public ResponseEntity<ErrorMessage> handleManagerException(TimelineException exception) {
	ErrorType errorType = exception.getErrorType();
	HttpStatus httpStatus = errorType.getHttpStatus();
	ErrorMessage errorMessage = createError(errorType,exception);
	errorMessage.setMessage(exception.getMessage());

	return new ResponseEntity<>(errorMessage,httpStatus);
  }

  @ExceptionHandler(MomentException.class)
  public ResponseEntity<ErrorMessage> handleManagerException(UserException exception) {
	ErrorType errorType = exception.getErrorType();
	HttpStatus httpStatus = errorType.getHttpStatus();
	ErrorMessage errorMessage = createError(errorType,exception);
	errorMessage.setMessage(exception.getMessage());

	return new ResponseEntity<>(errorMessage,httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {
	ErrorType errorType = ErrorType.INTERNAL_ERROR_SERVER;
	List<String> fields = new ArrayList<>();
	fields.add(exception.getMessage());
	ErrorMessage errorMessage = createError(errorType,exception);
	errorMessage.setFields(fields);
	return new ResponseEntity<>(createError(errorType,exception),errorType.getHttpStatus());
  }

  private ErrorMessage createError(ErrorType errorType,Exception exception) {
	System.out.println("Hata olustu: " + exception.getMessage());
	return ErrorMessage.builder()
					   .code(errorType.getCode())
					   .message(errorType.getMessage())
					   .build();
  }

  private ErrorMessage createError(ErrorType errorType,Exception exception,String message) {
	System.out.println("Hata olustu: " + exception.getMessage());
	return ErrorMessage.builder()
					   .code(errorType.getCode())
					   .message(message)
					   .build();
  }
}
