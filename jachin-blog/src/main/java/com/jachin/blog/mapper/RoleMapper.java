package com.jachin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jachin.blog.pojo.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:05
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
    /**
     * 通过用户 id 查询 roles
     * @param uid user id
     * @return roles
     */
    List<RoleEntity> listByUid(Long uid);
}
