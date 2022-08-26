package com.jachin.blog.service.impl;

import com.jachin.blog.pojo.dto.EmailDto;
import com.jachin.blog.service.EmailService;
import com.jachin.blog.service.RedisService;
import com.jachin.common.constant.Constants;
import com.jachin.common.constant.RabbitConstants;
import com.jachin.common.constant.RedisConstants;
import com.jachin.common.utils.CommonUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:35
 */
@Service
public class EmailServiceImpl implements EmailService {
    private final RedisService redisService;
    private final RabbitTemplate rabbitTemplate;


    public EmailServiceImpl(RedisService redisService, RabbitTemplate rabbitTemplate) {
        this.redisService = redisService;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void sendCode(String email) {
        EmailDto message = new EmailDto();
        message.setTo(email);
        message.setSubject("blog");
        String code = CommonUtils.getRandomCode();
        message.setContent("您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！");
        redisService.set(RedisConstants.EMAIL_CODE + email, code, Constants.CODE_EXPIRE);
        rabbitTemplate.convertAndSend(RabbitConstants.EMAIL_EXCHANGE, "*", message);
    }


}
