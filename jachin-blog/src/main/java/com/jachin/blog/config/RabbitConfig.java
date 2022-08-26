package com.jachin.blog.config;

import com.jachin.common.constant.RabbitConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 15:16
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue emailQueue() {
        return new Queue(RabbitConstants.EMAIL_QUEUE, true);
    }

    @Bean
    public FanoutExchange emailExchange() {
        return new FanoutExchange(RabbitConstants.EMAIL_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingEmailDirect() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
