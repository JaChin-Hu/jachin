package com.jachin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jachin.blog.pojo.entity.LoginInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 9:23
 */
@Mapper
public interface LoginInfoMapper extends BaseMapper<LoginInfoEntity> {
}
