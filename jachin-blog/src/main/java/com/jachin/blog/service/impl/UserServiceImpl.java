package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jachin.blog.mapper.UserMapper;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.service.UserService;
import com.jachin.common.constant.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 17:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userMapper.findOneByUsername(username);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userMapper.selectOne(new QueryWrapper<UserEntity>().eq("email", email));
    }

    @Override
    public PageInfo<UserEntity> list(Integer page, Integer size) {
        if (size > Constants.PAGE_MAX_SIZE) {
            size = Constants.PAGE_MAX_SIZE;
        }
        PageHelper.startPage(page, size);
        List<UserEntity> users = userMapper.selectList(null);
        return new PageInfo<>(users);
    }
}
