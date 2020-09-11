package com.spring.development.common.holder;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.datasource
 * @Author xuzhenkui
 * @Date 2020/2/26 18:27
 */
public class DataSourceHolder {
    private static final ThreadLocal<DataSource> contextHolder = new ThreadLocal<>();

    static {
        contextHolder.set(DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/development?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false")
                .username("root")
                .password("root").build());
    }

    public static DataSource getDataSource(){
        return contextHolder.get();
    }

    public static void setDataSource(DataSource dataSource){
        contextHolder.set(dataSource);
    }
}
