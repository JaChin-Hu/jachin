package com.jachin.blog.service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:35
 */
public interface EmailService {
    /**
     * 发送验证码
     * @param email 邮箱
     */
    void sendCode(String email);
}
