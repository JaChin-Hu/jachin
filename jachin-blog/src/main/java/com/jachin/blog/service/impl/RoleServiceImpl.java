package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jachin.blog.dao.RoleDao;
import com.jachin.blog.pojo.entity.RoleEntity;
import com.jachin.blog.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {
}
