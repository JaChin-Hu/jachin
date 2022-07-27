package com.jachin.blog.controller;

import com.github.pagehelper.PageInfo;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.service.UserService;
import com.jachin.common.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/14 17:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("@as.hasPerm('system:user:list')")
    @GetMapping("/list")
    public Result list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        PageInfo<UserEntity> users = userService.list(page, size);
        return Result.ok().data(users);
    }
}
