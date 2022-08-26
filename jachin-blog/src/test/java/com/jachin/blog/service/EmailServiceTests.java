package com.jachin.blog.service;

import com.jachin.common.constant.Constants;
import com.jachin.common.constant.RedisConstants;
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
        redisService.set(RedisConstants.EMAIL_CODE + "2061979589@qq.com", "123456", Constants.CODE_EXPIRE);
        System.out.println(redisService.get(RedisConstants.EMAIL_CODE + "2061979589@qq.com"));
    }
}
