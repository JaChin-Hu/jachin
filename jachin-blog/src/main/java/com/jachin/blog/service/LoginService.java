package com.jachin.blog.service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 18:40
 */
public interface LoginService {
    /**
     * 通过用户名和密码登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);
}
