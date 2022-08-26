package com.jachin.common.constant;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 18:10
 */
public class Constants {
    private Constants() {
    }

    public static final long CODE_EXPIRE = 15 * 60L;
    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String LOGIN_SUCCESS = "Success";
    public static final String LOGOUT = "Logout";
    public static final String REGISTER = "Register";
    public static final String LOGIN_FAIL = "Error";
    public static final Integer CAPTCHA_EXPIRATION = 2;
    public static final String TOKEN = "token";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String LOGIN_USER_KEY = "login_user_key";
    public static final String JWT_USERID = "userid";
    public static final String JWT_USERNAME = "";
    public static final String JWT_AVATAR = "avatar";
    public static final String JWT_CREATED = "created";
    public static final String JWT_AUTHORITIES = "authorities";
    public static final String RESOURCE_PREFIX = "/profile";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";
    public static final String PERMISSION_ADMIN = "*:*:*";
    public static final String DELIMITER = ",";

    public static final Integer PAGE_MAX_SIZE = 50;
    public static final String UNKNOWN = "unknown";
}
