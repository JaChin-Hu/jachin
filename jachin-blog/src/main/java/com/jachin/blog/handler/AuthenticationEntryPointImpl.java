package com.jachin.blog.handler;

import com.alibaba.fastjson2.JSON;
import com.jachin.common.constant.ExceptionCode;
import com.jachin.common.utils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/03 08:53
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(Result.error(ExceptionCode.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
