package com.jachin.common.utils;

import com.jachin.common.constant.ExceptionCode;
import com.jachin.common.constant.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/03/25 21:03
 */
public class Result extends HashMap<String, Object> {
    private static final String CODE_TAG = "code";
    private static final String MSG_TAG = "msg";
    private static final String DATA_TAG = "data";

    public Result() {
    }

    public Result(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public Result(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    public static Result ok() {
        Result result = new Result();
        result.put(CODE_TAG, HttpStatus.SUCCESS);
        return result;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put(CODE_TAG, HttpStatus.SUCCESS);
        r.put(MSG_TAG, msg);
        return r;
    }

    public static Result ok(String key, Object value) {
        Result r = new Result();
        r.put(key, value);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result error() {
        return error(HttpStatus.ERROR, "未知异常, 请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.ERROR, msg);
    }

    public static Result error(ExceptionCode exceptionCode) {
        return error(exceptionCode.getCode(), exceptionCode.getMessage());
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put(CODE_TAG, code);
        r.put(MSG_TAG, msg);
        return r;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
