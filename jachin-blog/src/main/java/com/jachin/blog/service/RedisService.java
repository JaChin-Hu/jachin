package com.jachin.blog.service;

import java.util.concurrent.TimeUnit;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 20:03
 */
public interface RedisService {
    /**
     * 保存 key, value
     * @param key key值
     * @param value value值
     */
    void set(String key, Object value);

    /**
     * 保存属性
     *
     * @param key   key值
     * @param value value值
     * @param time  过期时间 TimeUnit.SECONDS
     */
    void set(String key, Object value, long time);

    /**
     * 保存属性
     *
     * @param key   key
     * @param value value
     * @param time  time
     * @param unit  unit
     */
    void set(String key, Object value, long time, TimeUnit unit);

    /**
     * 获取属性
     *
     * @param key key值
     * @return 返回对象
     */
    Object get(String key);
}
