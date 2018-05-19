package com.cancer.dynamic.datasource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description:
 * @date 2018/5/1713:58
 */
public interface DynamicDataSourceProvider {
    DataSource   LoadDefaultDataSource ();
    Map<String, DataSource> loadslaveDataSource();

}
