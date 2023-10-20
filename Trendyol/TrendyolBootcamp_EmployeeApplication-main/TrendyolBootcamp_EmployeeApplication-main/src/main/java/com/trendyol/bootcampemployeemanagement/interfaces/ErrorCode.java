package com.trendyol.bootcampemployeemanagement.interfaces;

public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("EMP1000");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}