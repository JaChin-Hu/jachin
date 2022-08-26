package com.jachin.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class RoleEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String roleKey;
    private Integer sorted;
    private Character dataScope;
    private Boolean menuCheckStrictly;
    @TableField(value = "is_deleted")
    private Boolean deleted;
    @TableField(value = "is_enabled")
    private Boolean enabled;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
    private String remark;
}
