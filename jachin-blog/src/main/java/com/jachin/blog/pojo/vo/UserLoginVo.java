package com.jachin.blog.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/03 08:29
 */
@Data
@NoArgsConstructor
public class UserLoginVo {
    private String username;
    private String password;
}
