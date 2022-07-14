package com.jachin.blog.service;

import com.jachin.blog.pojo.entity.UserEntity;

import java.util.Set;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/14 15:40
 */
public interface PermissionService {
    /**
     * 查询用户角色
     *
     * @param userEntity userEntity
     * @return role keys
     */
    Set<String> getRolePermission(UserEntity userEntity);

    /**
     * 查询菜单
     *
     * @param userEntity UserEntity
     * @return menus
     */
    Set<String> getMenuPermission(UserEntity userEntity);
}
