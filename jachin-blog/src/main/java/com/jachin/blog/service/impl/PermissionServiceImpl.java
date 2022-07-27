package com.jachin.blog.service.impl;

import com.jachin.blog.mapper.MenuMapper;
import com.jachin.blog.mapper.RoleMapper;
import com.jachin.blog.pojo.entity.MenuEntity;
import com.jachin.blog.pojo.entity.RoleEntity;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.service.PermissionService;
import com.jachin.common.constant.Constants;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/14 15:40
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;

    public PermissionServiceImpl(RoleMapper roleMapper, MenuMapper menuMapper) {
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public Set<String> getRolePermission(UserEntity userEntity) {
        Set<String> roles = new HashSet<>();
        List<RoleEntity> roleEntities = roleMapper.listByUid(userEntity.getId());
        for (RoleEntity roleEntity : roleEntities) {
            roles.addAll(Arrays.asList(roleEntity.getRoleKey().trim().split(",")));
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(UserEntity userEntity) {
        if (Constants.ROLE_ADMIN.equals(roleMapper.selectById(userEntity.getId()).getRoleKey())) {
            Set<String> strings = new HashSet<>();
            strings.add("*:*:*");
            return strings;
        }
        return menuMapper.listByUid(userEntity.getId()).stream().map(MenuEntity::getPerms).collect(Collectors.toSet());
    }
}
