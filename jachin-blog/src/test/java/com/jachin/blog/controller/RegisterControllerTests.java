package com.jachin.blog.controller;

import com.alibaba.fastjson2.JSON;
import com.jachin.blog.pojo.vo.UserRegisterVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/25 15:38
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RegisterControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void sendCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/sendCode")
                .param("email", "2061979589@qq.com")
        );
    }

    @Test
    void register() throws Exception {
        UserRegisterVo registerVo = new UserRegisterVo();
        registerVo.setUsername("admin");
        registerVo.setPassword("admin123");
        registerVo.setEmail("2061979589@qq.com");
        registerVo.setCode("62a576");
        String s = JSON.toJSONString(registerVo);
        System.out.println(s);
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                .post("/register")
//                .content(s).contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print()).andReturn();
    }
}
