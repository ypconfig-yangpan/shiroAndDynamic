package com.cancer.dynamic.core;

import com.cancer.dynamic.datasource.DynamicDataSourceProvider;
import com.cancer.dynamic.datasource.DynamicDataSourceStrategy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.core
 * @Description: 路由器 核心类
 * @date 2018/5/1715:30
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private String[] slaveDataSourceKeys;

    private DynamicDataSourceProvider dynamicDataSourceProvider;
    private DynamicDataSourceStrategy dynamicDataSourceStrategy;
    @Override
    protected Object determineCurrentLookupKey() {

        String targetDataSource = DynamicDataSourceContextHolder.getDataSource();
        if (targetDataSource==null){
            targetDataSource="master";
        }else if (targetDataSource.isEmpty()){
             // 轮询从数据库
            targetDataSource = this.dynamicDataSourceStrategy.targetBackUpDataSource(slaveDataSourceKeys);
        }
        log.info("当前数据源====>{}",targetDataSource);
        return targetDataSource;
    }

    public void afterPropertiesSet() {
        DataSource defaultDataSource = this.dynamicDataSourceProvider.LoadDefaultDataSource();
        Map<String, DataSource> slaveDataSource = this.dynamicDataSourceProvider.loadslaveDataSource();
        // 获取所有的键
        Set<String> slaveNameS = slaveDataSource.keySet();
        this.slaveDataSourceKeys = (String[])slaveNameS.toArray(new String[slaveDataSource.size()]);

        Map<Object, Object> targetDataSource = new HashMap(slaveDataSource.size() + 1);
        // 设置主节点
        targetDataSource.put("master", defaultDataSource);
        // 设置从节点
        targetDataSource.putAll(slaveDataSource);
        // 设置默认节点
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }


    public String[] getSlaveDataSourceKeys() {
        return slaveDataSourceKeys;
    }

    public void setSlaveDataSourceKeys(String[] slaveDataSourceKeys) {
        this.slaveDataSourceKeys = slaveDataSourceKeys;
    }

    public DynamicDataSourceProvider getDynamicDataSourceProvider() {
        return dynamicDataSourceProvider;
    }

    public void setDynamicDataSourceProvider(DynamicDataSourceProvider dynamicDataSourceProvider) {
        this.dynamicDataSourceProvider = dynamicDataSourceProvider;
    }

    public DynamicDataSourceStrategy getDynamicDataSourceStrategy() {
        return dynamicDataSourceStrategy;
    }

    public void setDynamicDataSourceStrategy(DynamicDataSourceStrategy dynamicDataSourceStrategy) {
        this.dynamicDataSourceStrategy = dynamicDataSourceStrategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DynamicRoutingDataSource)) return false;
        DynamicRoutingDataSource that = (DynamicRoutingDataSource) o;
        return Arrays.equals(getSlaveDataSourceKeys(), that.getSlaveDataSourceKeys()) &&
                Objects.equals(getDynamicDataSourceProvider(), that.getDynamicDataSourceProvider()) &&
                Objects.equals(getDynamicDataSourceStrategy(), that.getDynamicDataSourceStrategy());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(getDynamicDataSourceProvider(), getDynamicDataSourceStrategy());
        result = 31 * result + Arrays.hashCode(getSlaveDataSourceKeys());
        return result;
    }

    @Override
    public String toString() {
        return "DynamicRoutingDataSource{" +
                "slaveDataSourceKeys=" + Arrays.toString(slaveDataSourceKeys) +
                ", dynamicDataSourceProvider=" + dynamicDataSourceProvider +
                ", dynamicDataSourceStrategy=" + dynamicDataSourceStrategy +
                '}';
    }
}
