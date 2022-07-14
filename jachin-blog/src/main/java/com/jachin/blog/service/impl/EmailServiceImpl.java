package com.jachin.blog.service.impl;

import com.jachin.blog.pojo.dto.EmailDto;
import com.jachin.blog.service.EmailService;
import com.jachin.blog.service.RedisService;
import com.jachin.common.constant.UserConstants;
import com.jachin.common.utils.CommonUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:35
 */
@Service
public class EmailServiceImpl implements EmailService {
    private final MailSender mailSender;
    private final RedisService redisService;
    private final RabbitTemplate rabbitTemplate;

    public EmailServiceImpl(MailSender mailSender, RedisService redisService, RabbitTemplate rabbitTemplate) {
        this.mailSender = mailSender;
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
        System.out.println(Arrays.toString(new GenericJackson2JsonRedisSerializer().serialize(code)));
        redisService.set("user:code:" + email, code, UserConstants.CODE_EXPIRE);
        rabbitTemplate.convertAndSend(UserConstants.EXCHANGE_DIRECT, UserConstants.CODE_ROUTING_KEY, message);
    }

    @RabbitListener(queues = {UserConstants.QUEUE_CODE})
    public void sendCode(Message message, EmailDto emailDto, Channel channel) throws IOException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("a2061979589@163.com");
        mailMessage.setTo(emailDto.getTo());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getContent());
        mailSender.send(mailMessage);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            throw new RuntimeException(e);
        }
    }
}
