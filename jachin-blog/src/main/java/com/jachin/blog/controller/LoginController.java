package com.jachin.blog.controller;

import com.jachin.blog.pojo.vo.UserLoginVo;
import com.jachin.blog.service.LoginService;
import com.jachin.common.constant.Constants;
import com.jachin.common.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:24
 */
@Slf4j
@Api(tags = {"用户登录"})
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginVo vo) {
        log.info(vo.toString());
        String token = loginService.login(vo.getUsername(), vo.getPassword());
        return Result.ok().put(Constants.TOKEN, token);
    }
}
