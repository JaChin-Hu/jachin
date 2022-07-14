package com.jachin.common.exception;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 16:04
 */
public class GlobalException extends RuntimeException {
    private String message;
    private String detailMessage;

    public GlobalException() {
    }

    public GlobalException(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public GlobalException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public GlobalException setMessage(String message) {
        this.message = message;
        return this;
    }
}
