package com.jachin.blog.controller;

import com.jachin.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/15 19:56
 */
@Api(tags = {"首页"})
@RestController
public class IndexController {

    @ApiOperation(value = "首页")
    @GetMapping("/")
    public Result index() {
        return Result.ok("Hello");
    }
}
