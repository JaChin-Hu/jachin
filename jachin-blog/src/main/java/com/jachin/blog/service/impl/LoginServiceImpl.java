package com.jachin.blog.service.impl;

import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.blog.service.LoginService;
import com.jachin.blog.service.TokenService;
import com.jachin.common.constant.HttpStatus;
import com.jachin.common.exception.ServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 18:41
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @Override
    public String login(String username, String password) {
        Authentication authentication;
        try {
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new ServiceException(HttpStatus.LOGIN_PASSWORD_NOT_MATCH, "用户名或密码错误");
            } else {
                throw new ServiceException(e.getMessage());
            }
        }
        UserDetailsDto userDetailsDto = (UserDetailsDto) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(userDetailsDto);
    }
}
