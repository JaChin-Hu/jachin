package com.jachin.common.annotation;

import com.jachin.common.constant.BusinessType;

import java.lang.annotation.*;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 9:29
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求参数
     */
    boolean saveParam() default true;

    /**
     * 是否保存响应结果
     */
    boolean saveResult() default true;
}
