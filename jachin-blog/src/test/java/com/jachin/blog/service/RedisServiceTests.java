package com.jachin.blog.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 16:20
 */
@SpringBootTest
class RedisServiceTests {
    @Autowired
    private RedisService redisService;

    @Test
    void set() {
        redisService.set("Hello", "Hello");
    }

    @Test
    void get() {
        Object hello = redisService.get("Hello");
        System.out.println(hello.toString());
    }
}
