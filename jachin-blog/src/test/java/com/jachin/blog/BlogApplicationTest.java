package com.jachin.blog;

import com.jachin.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogApplicationTest {
    @Autowired
    private UserService userService;

    @Test
    public void listTest() {
        userService.list().forEach(System.out::println);
    }
}
