package com.cancer.dynamic.reader;

import com.cancer.dynamic.reader.PropertiesDataSourceReader;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.datasource
 * @Description: 配置datasource 文件
 * @date 2018/5/1718:31
 */
@ConfigurationProperties(
        prefix = "spring.datasource"
)
@Slf4j
public class DynamicDataSourceProperties {
    public DynamicDataSourceProperties() {
    }

    @NestedConfigurationProperty
    private PropertiesDataSourceReader master = new PropertiesDataSourceReader();
    @NestedConfigurationProperty
    private Map<String, PropertiesDataSourceReader> slave = new HashMap();


    public PropertiesDataSourceReader getMaster() {
        return master;
    }

    public void setMaster(PropertiesDataSourceReader master) {
        this.master = master;
    }

    public Map<String, PropertiesDataSourceReader> getSlave() {
        return slave;
    }

    public void setSlave(Map<String, PropertiesDataSourceReader> slave) {
        this.slave = slave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DynamicDataSourceProperties)) return false;
        DynamicDataSourceProperties that = (DynamicDataSourceProperties) o;
        return Objects.equals(getMaster(), that.getMaster()) &&
                Objects.equals(getSlave(), that.getSlave());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getMaster(), getSlave());
    }

    @Override
    public String toString() {
        return "DynamicDataSourceProperties{" +
                "master=" + master +
                ", slave=" + slave +
                '}';
    }
}
