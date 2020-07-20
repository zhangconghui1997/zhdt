package com.bf.dt.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiDataSourceConfig {
    //这里primaryDataSource要和MysqlConfig中的primaryDataSource对应
    @Value(value = "${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value(value = "${spring.datasource.jdbc-url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String user;
    @Value(value = "${spring.datasource.password}")
    private String password;
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary // 定义主数据源
    //这里的prefix对应你application.properties的前缀
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.addFilters("stat");
        dataSource.addFilters("wall");
        return dataSource;
       /* return DataSourceBuilder.create().build();*/
//        return new DruidDataSource();
    }

    //配置Druid的监控
    //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String,String> initParam = new HashMap<>();
        initParam.put("loginUsername","admin");
        initParam.put("loginPassword","123456");
        initParam.put("allow","");  //默认就是允许所有访问
        initParam.put("deny","");		//默认访问

        servletRegistrationBean.setInitParameters(initParam);
        return  servletRegistrationBean;
    }

    //注册一个filters
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
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
