package com.jachin.blog.controller;

import com.alibaba.fastjson2.JSON;
import com.jachin.blog.pojo.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 16:36
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserRoleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUid(100L);
        userRole.setUid(1L);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/ur").content(JSON.toJSONString(userRole)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        System.out.println(mvcResult);
    }
}
