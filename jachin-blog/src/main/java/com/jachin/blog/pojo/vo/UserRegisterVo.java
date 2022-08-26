package com.jachin.blog.pojo.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:04
 */
@Data
public class UserRegisterVo {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 15, message = "用户名必须在2到20个字符之间")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 30, message = "密码必须在6到30个字符之间")
    private String password;
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotBlank(message = "验证码不能为空")
    private String code;
}
