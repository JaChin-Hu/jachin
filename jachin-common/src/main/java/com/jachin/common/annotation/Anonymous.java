package com.jachin.common.annotation;

import java.lang.annotation.*;

/**
 * 匿名访问不鉴权注解
 * @author JaChin
 * @version 1.0
 * @date 2022/07/12 16:20
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
}
