package com.cancer.dynamic.datasource;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description:
 * @date 2018/5/1715:36
 */
public interface DynamicDataSourceStrategy {
    String  targetBackUpDataSource(String[] backUps);
}
