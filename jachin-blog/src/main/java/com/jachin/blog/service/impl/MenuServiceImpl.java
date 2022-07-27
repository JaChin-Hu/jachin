package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jachin.blog.mapper.MenuMapper;
import com.jachin.blog.pojo.entity.MenuEntity;
import com.jachin.blog.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 10:12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
}
