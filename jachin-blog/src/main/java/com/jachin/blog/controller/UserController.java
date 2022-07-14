package com.jachin.blog.controller;

import com.jachin.common.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/14 17:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("@as.hasPerm('system:role:list')")
    public Result list() {
        return Result.ok();
    }
}
