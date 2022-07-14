package com.jachin.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 15:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDto {
    private String to;
    private String subject;
    private String content;
}
