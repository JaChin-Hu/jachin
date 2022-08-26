package com.jachin.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/25 15:10
 */
@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private MessageSource messageSource;

    @Test
    void i18nTest() {
        String register = messageSource.getMessage("register.success", null, LocaleContextHolder.getLocale());
        System.out.println(register);
    }
}
