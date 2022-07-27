package com.jachin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jachin.blog.pojo.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:05
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {
    /**
     * 根据角色 id 查询
     * @param rid 角色 id
     * @return List<RoleMenuEntity>
     */
    List<RoleMenuEntity> listByRid(Integer rid);
}
