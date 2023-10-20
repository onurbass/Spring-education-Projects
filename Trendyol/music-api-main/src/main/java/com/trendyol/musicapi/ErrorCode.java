package com.trendyol.musicapi;

public enum ErrorCode {
    TEST("TEST1000");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}