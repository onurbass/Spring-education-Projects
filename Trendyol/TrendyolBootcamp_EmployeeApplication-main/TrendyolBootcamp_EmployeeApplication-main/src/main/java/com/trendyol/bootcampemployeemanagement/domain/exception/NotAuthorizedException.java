package com.trendyol.bootcampemployeemanagement.domain.exception;

public class NotAuthorizedException extends RuntimeException {

    private NotAuthorizedException() {

    }

    public static NotAuthorizedException create() {
        return new NotAuthorizedException();
    }

    @Override
    public String toString() {
        return "Authentication failed. You shall no pass!";
    }
}
