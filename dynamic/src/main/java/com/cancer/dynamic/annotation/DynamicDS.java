package com.cancer.dynamic.annotation;

import java.lang.annotation.*;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.annotation
 * @Description:
 * @date 2018/5/1714:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDS {
    String  value() default "";
}
