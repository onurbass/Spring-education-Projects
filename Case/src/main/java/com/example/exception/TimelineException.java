package com.example.exception;

import lombok.Getter;

@Getter
public class TimelineException extends RuntimeException{
  private final ErrorType errorType;

  public TimelineException(ErrorType errorType) {
	super(errorType.getMessage());
	this.errorType = errorType;
  }

  public TimelineException(ErrorType errorType,String customMessage){
	super(customMessage);
	this.errorType=errorType;
  }
}
