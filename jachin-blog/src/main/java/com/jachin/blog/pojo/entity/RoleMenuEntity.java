package com.jachin.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:24
 */
@Data
@TableName("sys_role_menu")
public class RoleMenuEntity {
    private Long rid;
    private Long mid;
}
