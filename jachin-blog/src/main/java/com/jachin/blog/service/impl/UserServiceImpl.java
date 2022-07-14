package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jachin.blog.dao.RoleDao;
import com.jachin.blog.dao.UserDao;
import com.jachin.blog.dao.UserRoleDao;
import com.jachin.blog.pojo.entity.RoleEntity;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.pojo.entity.UserRoleEntity;
import com.jachin.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 17:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private final UserDao userDao;
    private final UserRoleDao userRoleDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, UserRoleDao userRoleDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserEntity findByUsername(String username) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userDao.selectOne(wrapper);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDao.selectOne(new QueryWrapper<UserEntity>().eq("email", email));
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        return userDao.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
    }

    @Override
    public List<RoleEntity> getRole(Integer id) {
        List<UserRoleEntity> urs = userRoleDao.selectList(new QueryWrapper<UserRoleEntity>().eq("uid", id));
        return urs.stream().map(ur -> roleDao.selectById(ur.getRid())).collect(Collectors.toList());
    }
}
