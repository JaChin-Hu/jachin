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

    SUCCESS_login(200, "用户登录成功"),
    SUCCESS_logout(200, "用户退出成功");
    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
