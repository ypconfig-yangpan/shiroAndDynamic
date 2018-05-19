package com.cancer.dynamic.aop;

import com.cancer.dynamic.annotation.DynamicDS;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.aop
 * @Description:
 * @date 2018/5/1715:10
 */
public class DynamicDatasourceAnnotationAdvisor extends StaticMethodMatcherPointcutAdvisor {
    public DynamicDatasourceAnnotationAdvisor() {
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return method.isAnnotationPresent(DynamicDS.class);
    }
}
