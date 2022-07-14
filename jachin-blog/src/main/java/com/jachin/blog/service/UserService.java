package com.jachin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jachin.blog.pojo.entity.RoleEntity;
import com.jachin.blog.pojo.entity.UserEntity;

import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 17:29
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    UserEntity findByUsername(String username);

    /**
     * 通过邮箱查找用户
     * @param email
     * @return
     */
    UserEntity findByEmail(String email);

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    UserEntity loadUserByUsername(String username);

    /**
     * 根据用户id获取角色
     * @param id id
     * @return 角色列表
     */
    List<RoleEntity> getRole(Integer id);
}
