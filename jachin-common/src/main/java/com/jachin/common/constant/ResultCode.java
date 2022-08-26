package com.jachin.common.constant;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:47
 */
public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    LOGIN_SUCCESS(200, "用户登录成功"),
    LOGOUT_SUCCESS(200, "用户退出成功");
    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
