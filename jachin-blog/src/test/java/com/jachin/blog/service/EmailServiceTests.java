package com.jachin.blog.service;

import com.jachin.common.constant.UserConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTests {
    @Autowired
    EmailService emailService;
    @Autowired
    RedisService redisService;

    @Test
    public void sendCode() {
        emailService.sendCode("2061979589@qq.com");
    }

    @Test
    public void getCode() {
        redisService.set(UserConstants.CODE_PREFIX + "2061979589@qq.com", "123456", UserConstants.CODE_EXPIRE);
        System.out.println(redisService.get(UserConstants.CODE_PREFIX + "2061979589@qq.com"));
    }
}
