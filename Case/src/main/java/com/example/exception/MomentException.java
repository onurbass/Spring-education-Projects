package com.example.exception;

import lombok.Getter;

@Getter
public class MomentException extends RuntimeException {

  private final ErrorType errorType;

  public MomentException(ErrorType errorType) {
	super(errorType.getMessage());
	this.errorType = errorType;
  }

  public MomentException(ErrorType errorType,String customMessage) {
	super(customMessage);
	this.errorType = errorType;
  }

}
