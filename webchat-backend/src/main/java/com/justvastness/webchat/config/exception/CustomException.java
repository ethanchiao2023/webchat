package com.justvastness.webchat.config.exception;

public class CustomException extends RuntimeException {
    private String errorMsg;

    public CustomException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
