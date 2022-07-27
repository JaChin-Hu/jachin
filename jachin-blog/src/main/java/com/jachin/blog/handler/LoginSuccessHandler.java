package com.jachin.blog.handler;

import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.blog.service.LogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/16 20:13
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final LogService logService;

    public LoginSuccessHandler(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetailsDto userDetailsDto = (UserDetailsDto) authentication.getPrincipal();
        logService.saveLoginInfo(userDetailsDto, request);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
