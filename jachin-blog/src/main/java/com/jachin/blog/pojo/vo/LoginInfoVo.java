package com.jachin.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/16 20:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoVo {
    private String username;
    private Date date;
    private String ip;
    private String hostname;
    private String os;
    private String browser;
}
