package com.cancer.dynamic.aop;

import java.lang.reflect.Method;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.aop
 * @Description:
 * @date 2018/5/1715:18
 */
public class DynamicDatasourceConfigurationInterceptor extends AbstractDynamicDataSourceInterceptor {
    public DynamicDatasourceConfigurationInterceptor() {
    }

    @Override
    String currentDataSource(Method method, Object[] objects, Object o) {
        return null;
    }
}
