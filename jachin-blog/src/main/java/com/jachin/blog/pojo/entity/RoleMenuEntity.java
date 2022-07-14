package com.jachin.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:24
 */
@Data
@TableName("sys_role_menu")
public class RoleMenuEntity {
    private Integer rid;
    private Integer mid;
}
