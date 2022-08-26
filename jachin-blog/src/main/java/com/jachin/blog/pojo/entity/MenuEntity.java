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
@TableName("sys_menu")
public class MenuEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer pid;
    private Integer sorted;
    private String path;
    private String component;
    private String query;
    @TableField(value = "is_frame")
    private String frame;
    @TableField(value = "is_cache")
    private String cache;
    private Character type;
    @TableField(value = "is_visible")
    private Boolean visible;
    @TableField(value = "is_enabled")
    private Boolean enabled;
    private String perms;
    private String icon;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    private String createBy;
    private String updateBy;
    private String remark;
}
