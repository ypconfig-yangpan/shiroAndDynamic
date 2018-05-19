package com.cancer.dynamic.springboot;

import com.cancer.dynamic.aop.DynamicDatasourceAnnotationAdvisor;
import com.cancer.dynamic.aop.DynamicDatasourceAnnotationInterceptor;
import com.cancer.dynamic.core.DynamicRoutingDataSource;
import com.cancer.dynamic.datasource.*;
import com.cancer.dynamic.reader.DynamicDataSourceProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author yangpan
 * @Title: dynamic
 * @Package com.cancer.dynamic.springboot
 * @Description:
 * @date 2018/5/1719:06
 */

@Configuration
@EnableConfigurationProperties({DynamicDataSourceProperties.class})
//表示存在对应的Class文件时才会去解析DataSource,不存在不解析,所以加载了 maven坐标的 才会自动配置
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class DynamicDataSourceAutoConfiguration {

    private DynamicDataSourceProperties dynamicDataSourceProperties;

    public DynamicDataSourceAutoConfiguration(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceStrategy dynamicDataSourceStrategy() {
        return new RotateDynamicDataSourceStrategy();
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new PropertiesDynamicDataSourceProvider(this.dynamicDataSourceProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicRoutingDataSource dynamicDataSource(DynamicDataSourceProvider dynamicDataSourceProvider, DynamicDataSourceStrategy dynamicDataSourceStrategy) {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        dynamicRoutingDataSource.setDynamicDataSourceProvider(dynamicDataSourceProvider);
        dynamicRoutingDataSource.setDynamicDataSourceStrategy(dynamicDataSourceStrategy);
        return dynamicRoutingDataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDatasourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        DynamicDatasourceAnnotationAdvisor advisor = new DynamicDatasourceAnnotationAdvisor();
        advisor.setAdvice(new DynamicDatasourceAnnotationInterceptor());
        advisor.setOrder(-2147483648);
        return advisor;
    }
}
