package com.jachin.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 9:16
 */
@Data
@TableName("sys_ope_log")
public class OpeLogEntity {
    /**
     * 日志id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型(0其他 1新增 2修改 3删除)
     */
    private Integer businessType;

    /**
     * 请求url
     */
    private String url;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 操作人员
     */
    private String username;

    /**
     * 主机地址
     */
    private String ip;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 操作描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
