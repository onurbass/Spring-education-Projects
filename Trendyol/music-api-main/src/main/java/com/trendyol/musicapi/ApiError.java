package com.trendyol.musicapi;

public class ApiError {
    private Error error;

    private ApiError(int status, String message, String errorCode) {
        this.error = new Error(status, message, errorCode);
    }

    public ApiError(Error error) {
        this.error = error;
    }

    public static ApiError create(int status, String message, String errorCode) {
        return new ApiError(status, message, errorCode);
    }

    public static ApiError internalServerError() {
        return new ApiError(500, "The execution of the service failed in some way", "9999");
    }

    public static ApiError endPointNotFound(String path) {
        return new ApiError(404, "The endpoint you've requested not found, path:" + path, "9998");
    }

    public Error getError() {
        return error;
    }
}