package com.starnoh.turf.bookingsystem.backend.util;

public class ApiResponse<T> {

    private boolean success;
    private T data;
    private Meta meta;
    private ErrorResponse error;

    // constructors
    public ApiResponse(boolean success, T data, Meta meta, ErrorResponse error) {
        this.success = success;
        this.data = data;
        this.meta = meta;
        this.error = error;
    }

    // static factory methods (VERY CLEAN)
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                data,
                new Meta(),
                null
        );
    }

    public static <T> ApiResponse<T> error(ErrorResponse error) {
        return new ApiResponse<>(
                false,
                null,
                null,
                error
        );
    }

    // getters
    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public Meta getMeta() { return meta; }
    public ErrorResponse getError() { return error; }
}