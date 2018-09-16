package com.cancer.dynamic.aop;

import com.cancer.dynamic.core.DynamicDataSourceContextHolder;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.aop
 * @Description:111
 * @date 2018/5/1713:45
 */
public abstract class AbstractDynamicDataSourceInterceptor implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String dataSource = this.currentDataSource(method, objects, o);
        DynamicDataSourceContextHolder.setDataSource(dataSource);
    }

    abstract String currentDataSource(Method method, Object[] objects, Object o);
}
