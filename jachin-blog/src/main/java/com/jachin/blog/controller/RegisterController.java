package com.jachin.blog.controller;

import com.jachin.blog.pojo.vo.UserRegisterVo;
import com.jachin.blog.service.EmailService;
import com.jachin.blog.service.RegisterService;
import com.jachin.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:26
 */
@RestController
public class RegisterController {
    private final RegisterService registerService;
    private final EmailService emailService;

    public RegisterController(RegisterService registerService, EmailService emailService) {
        this.registerService = registerService;
        this.emailService = emailService;
    }

    @PostMapping("/sendCode")
    public Result sendCode(@RequestParam("email") String email) {
        emailService.sendCode(email);
        return Result.ok();
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterVo vo) {
        String msg = registerService.register(vo);
        return StringUtils.isEmpty(msg) ? Result.ok("注册成功!") : Result.error(msg);
    }
}
