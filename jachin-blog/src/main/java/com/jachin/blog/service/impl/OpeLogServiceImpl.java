package com.jachin.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jachin.blog.mapper.OpeLogMapper;
import com.jachin.blog.pojo.entity.OpeLogEntity;
import com.jachin.blog.service.OpeLogService;
import org.springframework.stereotype.Service;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 9:25
 */
@Service
public class OpeLogServiceImpl extends ServiceImpl<OpeLogMapper, OpeLogEntity> implements OpeLogService {
}
