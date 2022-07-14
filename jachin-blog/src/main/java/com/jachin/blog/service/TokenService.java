package com.jachin.blog.service;

import com.jachin.blog.pojo.dto.UserDetailsDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 16:07
 */
public interface TokenService {
    /**
     * 从 Header 中取出 token
     * @param request request
     * @return token
     */
    String getToken(HttpServletRequest request);

    /**
     * 验证令牌有效期
     * @param userDetailsDto UserDetailsDto
     */
    void verifyToken(UserDetailsDto userDetailsDto);

    /**
     * 从 Header 中取出 token 查询用户
     * @param request request
     * @return UserDetailsDto
     */
    UserDetailsDto getUserDetails(HttpServletRequest request);

    /**
     * 根据 UserDetailsDto 创建 token
     * @param userDetailsDto UserDetailsDto
     * @return token
     */
    String createToken(UserDetailsDto userDetailsDto);

    /**
     * 刷新 token
     * @param userDetailsDto UserDetailsDto
     */
    void refreshToken(UserDetailsDto userDetailsDto);

    /**
     * 通过 token 获取用户名
     * @param token token
     * @return username
     */
    String getUsername(String token);
}
