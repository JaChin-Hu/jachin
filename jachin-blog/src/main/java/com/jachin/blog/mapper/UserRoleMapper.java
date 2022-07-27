package com.jachin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jachin.blog.pojo.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:05
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {
    /**
     * 通过用户 id 查找 所有 userRole
     * @param uid
     * @return
     */
    List<UserRoleEntity> listByUid(Integer uid);
}
