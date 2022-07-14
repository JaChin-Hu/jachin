package com.jachin.blog.service.impl;

import com.jachin.blog.dao.MenuDao;
import com.jachin.blog.dao.RoleDao;
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
    private final RoleDao roleDao;
    private final MenuDao menuDao;

    public PermissionServiceImpl(RoleDao roleDao, MenuDao menuDao) {
        this.roleDao = roleDao;
        this.menuDao = menuDao;
    }

    @Override
    public Set<String> getRolePermission(UserEntity userEntity) {
        Set<String> roles = new HashSet<>();
        List<RoleEntity> roleEntities = roleDao.listByUid(userEntity.getId());
        for (RoleEntity roleEntity : roleEntities) {
            roles.addAll(Arrays.asList(roleEntity.getRoleKey().trim().split(",")));
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(UserEntity userEntity) {
        if (Constants.ROLE_ADMIN.equals(roleDao.selectById(userEntity.getId()).getRoleKey())) {
            Set<String> strings = new HashSet<>();
            strings.add("*:*:*");
            return strings;
        }
        return menuDao.listByUid(userEntity.getId()).stream().map(MenuEntity::getPerms).collect(Collectors.toSet());
    }
}
