package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jachin.blog.dao.RoleMenuDao;
import com.jachin.blog.pojo.entity.RoleMenuEntity;
import com.jachin.blog.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {
}
