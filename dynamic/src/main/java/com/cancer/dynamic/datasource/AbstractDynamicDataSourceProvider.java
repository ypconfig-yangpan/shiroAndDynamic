package com.cancer.dynamic.datasource;

import com.cancer.dynamic.reader.PropertiesDataSourceReader;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description:
 * @date 2018/5/1716:13
 */
@Slf4j
public abstract class AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {
    public AbstractDynamicDataSourceProvider() {
    }

    protected DataSource createDataSource(PropertiesDataSourceReader reader){
        //如果是德鲁伊 type=com.alibaba.druid.pool.DruidDataSource
        Class<? extends DataSource> type = reader.getType();
        return reader.initializeDataSourceBuilder().build();
    }

}
