package com.jachin.blog.pojo.vo;

import lombok.Data;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:04
 */
@Data
public class UserRegisterVo {
    private String username;
    private String password;
    private String email;
    private String code;
}
