package com.cancer.dynamic.aop;

import com.cancer.dynamic.annotation.DynamicDS;

import java.lang.reflect.Method;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.aop
 * @Description:
 * @date 2018/5/1715:14
 */
public class DynamicDatasourceAnnotationInterceptor extends AbstractDynamicDataSourceInterceptor {

    public DynamicDatasourceAnnotationInterceptor() {
    }


    @Override
    String currentDataSource(Method method, Object[] objects, Object o) {
        DynamicDS dataSource = method.getDeclaredAnnotation(DynamicDS.class);
        return dataSource.value();
    }
}
