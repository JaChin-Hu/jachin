package com.jachin.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.blog.service.RedisService;
import com.jachin.blog.service.TokenService;
import com.jachin.common.constant.Constants;
import com.jachin.common.constant.UserConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 16:07
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expire}")
    private Integer expire;

    private final long MILLIS_MINUTE_TEN = 2 * 24 * 60 * 60 * 1000L;

    private final RedisService redisService;

    public TokenServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void verifyToken(UserDetailsDto userDetailsDto) {
        String token = userDetailsDto.getToken();
        if (parseToken(token).getExpiration().getTime() - System.currentTimeMillis() <= MILLIS_MINUTE_TEN) {
            refreshToken(userDetailsDto);
        }
    }

    @Override
    public UserDetailsDto getUserDetails(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                String username = getUsername(token);
                Object o = redisService.get(UserConstants.USER_DETAILS + username);
                UserDetailsDto userDetailsDto = JSON.parseObject(o.toString(), UserDetailsDto.class);
                return userDetailsDto;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String createToken(UserDetailsDto userDetailsDto) {
        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                .setSubject(userDetailsDto.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public void refreshToken(UserDetailsDto userDetailsDto) {
        String token = createToken(userDetailsDto);
        userDetailsDto.setToken(token);
        redisService.set(UserConstants.USER_DETAILS + userDetailsDto.getUsername(), userDetailsDto, 7, TimeUnit.DAYS);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
