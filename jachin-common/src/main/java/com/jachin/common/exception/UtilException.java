package com.jachin.common.exception;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/12 16:05
 */
public class UtilException extends RuntimeException {
    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
