package com.justvastness.webchat.config.result;

import lombok.Getter;
import lombok.Setter;


public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "Successful");
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Successful", data);
    }

    public static <T> ApiResponse<T> fail(int code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }
}
