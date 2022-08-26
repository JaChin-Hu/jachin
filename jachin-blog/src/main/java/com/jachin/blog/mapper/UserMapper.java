package com.jachin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jachin.blog.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:05
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    UserEntity findOneByUsername(String username);

    /**
     * 通过邮箱查找用户
     * @param email 邮箱
     * @return 用户
     */
    UserEntity findOneByEmail(String email);

    /**
     * 通过用户 id 查找 用户
     * @param id id
     * @return 用户
     */
    UserEntity findById(Integer id);
}
