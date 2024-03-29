package com.jachin.blog.service.impl;

import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.blog.service.LoginService;
import com.jachin.blog.service.RedisService;
import com.jachin.blog.service.TokenService;
import com.jachin.common.constant.HttpStatus;
import com.jachin.common.constant.UserConstants;
import com.jachin.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 18:41
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final RedisService redisService;

    public LoginServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService, RedisService redisService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.redisService = redisService;
    }


    @Override
    public String login(String username, String password) {
        Authentication authentication;
        try {
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new ServiceException(HttpStatus.LOGIN_PASSWORD_NOT_MATCH, "用户名或密码错误");
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        UserDetailsDto userDetailsDto = (UserDetailsDto) authentication.getPrincipal();
        String token = tokenService.createToken(userDetailsDto);
        userDetailsDto.setToken(token);
        redisService.set(UserConstants.USER_DETAILS + userDetailsDto.getUsername(), userDetailsDto, 7, TimeUnit.DAYS);
        return token;
    }
}
