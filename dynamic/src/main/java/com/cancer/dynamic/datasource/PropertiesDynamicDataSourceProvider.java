package com.cancer.dynamic.datasource;

import com.cancer.dynamic.reader.DynamicDataSourceProperties;
import com.cancer.dynamic.reader.PropertiesDataSourceReader;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description:
 * @date 2018/5/1714:09
 */
public class PropertiesDynamicDataSourceProvider extends AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider{

    DynamicDataSourceProperties dynamicDataSourceProperties;

    public PropertiesDynamicDataSourceProvider(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }

    @Override
    public DataSource LoadDefaultDataSource() {
        return this.createDataSource(this.dynamicDataSourceProperties.getMaster());
    }

    @Override
    public Map<String, DataSource> loadslaveDataSource() {
        Map<String, PropertiesDataSourceReader> slave = this.dynamicDataSourceProperties.getSlave();
        Map<String, DataSource> dataSourceMap = new HashMap(slave.size());
        slave.forEach((k,v)->{
            DataSource put = dataSourceMap.put(k, this.createDataSource(v));
        });

        return dataSourceMap;
    }


}
