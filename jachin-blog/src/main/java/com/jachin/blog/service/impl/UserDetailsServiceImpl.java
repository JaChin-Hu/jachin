package com.jachin.blog.service.impl;

import com.jachin.blog.mapper.UserMapper;
import com.jachin.blog.pojo.dto.UserDetailsDto;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.service.PermissionService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/03 10:26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final PermissionService permissionService;

    public UserDetailsServiceImpl(UserMapper userMapper, PermissionService permissionService) {
        this.userMapper = userMapper;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetailsDto loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMapper.findOneByUsername(username);
        if (user != null) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setUser(user);
            userDetailsDto.setRoles(permissionService.getRolePermission(user));
            userDetailsDto.setPermissions(permissionService.getMenuPermission(user));
            return userDetailsDto;
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
