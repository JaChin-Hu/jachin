package com.jachin.blog.service;

import com.jachin.blog.pojo.vo.UserRegisterVo;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/13 19:25
 */
public interface RegisterService {
    /**
     * 注册用户
     * @param vo register info
     * @return msg
     */
    String register(UserRegisterVo vo);
}
