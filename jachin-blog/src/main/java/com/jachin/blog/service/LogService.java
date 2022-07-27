package com.jachin.blog.service;

import com.jachin.blog.pojo.dto.UserDetailsDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/17 14:47
 */
public interface LogService {
    /**
     * 保存登录信息
     *
     * @param userDetailsDto UserDetailsDto
     * @param request        HttpServletRequest
     */
    void saveLoginInfo(UserDetailsDto userDetailsDto, HttpServletRequest request);
}
