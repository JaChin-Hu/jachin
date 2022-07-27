package com.jachin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.jachin.blog.pojo.entity.UserEntity;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 17:29
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    UserEntity findByUsername(String username);

    /**
     * 通过邮箱查找用户
     *
     * @param email
     * @return
     */
    UserEntity findByEmail(String email);

    /**
     * 查询用户信息
     *
     * @param page 页数
     * @param size size
     * @return PageInfo
     */
    PageInfo<UserEntity> list(Integer page, Integer size);
}
