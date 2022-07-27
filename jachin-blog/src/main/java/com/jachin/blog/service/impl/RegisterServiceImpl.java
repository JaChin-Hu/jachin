package com.jachin.blog.service.impl;

import com.jachin.blog.mapper.UserMapper;
import com.jachin.blog.mapper.UserRoleMapper;
import com.jachin.blog.pojo.entity.UserEntity;
import com.jachin.blog.pojo.entity.UserRoleEntity;
import com.jachin.blog.pojo.vo.UserRegisterVo;
import com.jachin.blog.service.RedisService;
import com.jachin.blog.service.RegisterService;
import com.jachin.common.constant.UserConstants;
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
        String password = vo.getPassword();
        String email = vo.getEmail();
        String code = redisService.get(UserConstants.CODE_PREFIX + vo.getEmail()).toString();

        if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (userMapper.findOneByUsername(username) != null) {
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
            userRoleMapper.insert(new UserRoleEntity(user.getId(), 2));
        }
        return msg;
    }
}
