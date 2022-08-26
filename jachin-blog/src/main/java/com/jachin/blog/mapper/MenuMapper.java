package com.jachin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jachin.blog.pojo.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/06/30 18:05
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {
    /**
     * 通过用户 id 查询 菜单
     * @param uid user id
     * @return menus
     */
    List<MenuEntity> listByUid(Long uid);
}
