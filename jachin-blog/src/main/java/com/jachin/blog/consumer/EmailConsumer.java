package com.jachin.blog.consumer;

import com.jachin.blog.pojo.dto.EmailDto;
import com.jachin.common.constant.RabbitConstants;
import com.jachin.common.exception.ServiceException;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/24 15:11
 */
@Component
@RabbitListener(queues = {RabbitConstants.EMAIL_QUEUE})
public class EmailConsumer {
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender javaMailSender;

    public EmailConsumer(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @RabbitHandler
    public void process(Message message, EmailDto emailDto, Channel channel) throws IOException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(emailDto.getTo());
        mailMessage.setSubject(emailDto.getSubject());
        mailMessage.setText(emailDto.getContent());
        javaMailSender.send(mailMessage);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            throw new ServiceException();
        }
    }
}
