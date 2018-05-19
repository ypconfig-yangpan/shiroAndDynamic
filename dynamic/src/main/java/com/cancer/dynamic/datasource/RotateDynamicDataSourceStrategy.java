package com.cancer.dynamic.datasource;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description: 负载均衡
 * @date 2018/5/1715:47
 */
public class RotateDynamicDataSourceStrategy implements DynamicDataSourceStrategy{
    public RotateDynamicDataSourceStrategy() {
    }
    private AtomicInteger count = new AtomicInteger(0);
    @Override
    public String targetBackUpDataSource(String[] slaves) {
        int number = this.count.getAndAdd(1);
        return slaves[number % slaves.length];
    }
}
