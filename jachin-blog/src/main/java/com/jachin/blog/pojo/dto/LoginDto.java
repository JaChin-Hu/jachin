package com.jachin.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/12 18:59
 */
@Data
@AllArgsConstructor
public class LoginDto {
    private String ip;
    private LocalDateTime date;
}
