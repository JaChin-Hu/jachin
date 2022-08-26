package com.jachin.blog.controller;

import com.jachin.blog.pojo.entity.UserRoleEntity;
import com.jachin.blog.service.UserRoleService;
import com.jachin.common.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 16:30
 */
@RequestMapping("/ur")
@RestController
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("")
    public Result insert(@RequestBody UserRoleEntity userRoleEntity) {
        userRoleService.save(userRoleEntity);
        return Result.ok("添加关系成功");
    }


}
