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
 * @date 2022/06/30 17:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String nickname;
    private String avatar;
    private String type;
    private Character sex;
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
