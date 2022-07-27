package com.jachin.blog.service.impl;

import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.blog.pojo.vo.LoginInfoVo;
import com.jachin.blog.service.LogService;
import com.jachin.blog.service.RedisService;
import com.jachin.blog.utils.IpUtils;
import com.jachin.common.constant.UserConstants;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/17 14:48
 */
@Service
public class LogServiceImpl implements LogService {
    private final RedisService redisService;

    public LogServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Async
    @Override
    public void saveLoginInfo(UserDetailsDto userDetailsDto, HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        LoginInfoVo loginInfoVo = LoginInfoVo.builder()
                .date(new Date())
                .ip(IpUtils.getIpAddress(request))
                .username(userDetailsDto.getUsername())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOperatingSystem().getName())
                .build();
        redisService.set(UserConstants.LOGIN_INFO + userDetailsDto.getUsername(), loginInfoVo);
    }
}
