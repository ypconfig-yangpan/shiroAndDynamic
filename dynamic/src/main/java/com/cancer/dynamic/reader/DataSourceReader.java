package com.cancer.dynamic.reader;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description:
 * @date 2018/5/1713:55
 */
public interface DataSourceReader {

    public DataSource read();
}
