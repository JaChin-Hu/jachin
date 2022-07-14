package com.jachin.common.constant;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/03/25 21:03
 */
public enum ExceptionCode {
    /**
     * 10000,未知错误
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    EMAIL_CODE(132, "邮箱验证码错误"),
    USERNAME_EXISTING(153, "用户名已存在"),
    EMAIL_EXISTING(154, "邮箱已注册"),
    UNAUTHORIZED(401, "用户未认证"),
    USER_PERMISSION(403, "用户权限不足"),
    LOGIN_FAILED(405," 用户名或密码错误");

    private final Integer code;
    private final String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
