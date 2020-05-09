package com.bf.dt.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MultiDataSourceConfig {
    //这里primaryDataSource要和MysqlConfig中的primaryDataSource对应
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary // 定义主数据源
    //这里的prefix对应你application.properties的前缀
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    //这里secondaryDataSource要和OracleConfig中的secondaryDataSource对应
    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    //这里的prefix对应你application.properties的前缀
    @ConfigurationProperties(prefix = "custom.datasource.ds1")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
