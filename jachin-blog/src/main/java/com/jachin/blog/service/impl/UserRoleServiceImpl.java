package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jachin.blog.mapper.UserRoleMapper;
import com.jachin.blog.pojo.entity.UserRoleEntity;
import com.jachin.blog.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
}
