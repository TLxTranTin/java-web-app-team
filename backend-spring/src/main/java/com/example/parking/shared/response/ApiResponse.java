package com.example.parking.shared.response;

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private String errorCode;
    private T data;

    private ApiResponse(boolean success, String message, String errorCode, T data) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, null, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null, null);
    }

    public static <T> ApiResponse<T> error(String errorCode, String message) {
        return new ApiResponse<>(false, message, errorCode, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, "ERROR", null);
    }

    public boolean isSuccess()   { return success; }
    public String getMessage()   { return message; }
    public String getErrorCode() { return errorCode; }
    public T getData()           { return data; }
}