package com.jachin.blog.service.impl;

import com.jachin.blog.mapper.UserMapper;
import com.jachin.blog.mapper.UserRoleMapper;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.pojo.entity.UserRoleEntity;
import com.jachin.blog.pojo.vo.UserRegisterVo;
import com.jachin.blog.service.RedisService;
import com.jachin.blog.service.RegisterService;
import com.jachin.common.constant.RedisConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 21:18
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public RegisterServiceImpl(UserMapper userMapper, UserRoleMapper userRoleMapper, PasswordEncoder passwordEncoder, RedisService redisService) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public String register(UserRegisterVo vo) {
        String msg = "";
        String username = vo.getUsername();
        String email = vo.getEmail();
        String code = redisService.get(RedisConstants.EMAIL_CODE + vo.getEmail()).toString();

        if (userMapper.findOneByUsername(username) != null) {
            msg = "保存用户'" + username + "'失败，用户名已存在";
        } else if (userMapper.findOneByEmail(email) != null) {
            msg = "邮箱已使用";
        } else if (!StringUtils.equals(code, vo.getCode())) {
            msg = "验证码错误";
        } else {
            UserEntity user = new UserEntity();
            user.setUsername(vo.getUsername());
            user.setNickname(vo.getUsername());
            user.setPassword(passwordEncoder.encode(vo.getPassword()));
            user.setEmail(vo.getEmail());
            userMapper.insert(user);
            userRoleMapper.insert(new UserRoleEntity(user.getId(), 2L));
        }
        return msg;
    }
}
