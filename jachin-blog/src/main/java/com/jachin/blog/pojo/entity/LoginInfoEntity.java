package com.jachin.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 15:14
 */
@Data
@TableName("login_info")
public class LoginInfoEntity {
    /**
     * 登录信息id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态: 0 成功 1失败
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 登录时间
     */
    private Date createTime;
}
