package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class KullaniciManagerException extends RuntimeException{

    private final ErrorType errorType;

    public KullaniciManagerException(ErrorType errorType, String  customMessage ) {
        super(customMessage);
        this.errorType = errorType;

    }

    public KullaniciManagerException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
