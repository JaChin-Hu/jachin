package com.jachin.blog.utils;

import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.common.constant.HttpStatus;
import com.jachin.common.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 15:54
 */
public class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     **/
    public static UserDetailsDto getLoginUser() {
        try {
            return (UserDetailsDto) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.UNAUTHORIZED, "获取用户信息异常");
        }
    }
}
