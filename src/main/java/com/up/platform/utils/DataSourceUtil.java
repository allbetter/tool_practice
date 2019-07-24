package com.up.platform.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.up.platform.entity.DBManage;
import com.up.platform.manager.DataSourceContextHolder;
import com.up.platform.manager.DynamicDataSource;

import java.util.Map;

public class DataSourceUtil {

    public static void masterDBSource() {
        DataSourceContextHolder.setDBType("master");
    }

    public static void defaultDBSource() {
        DataSourceContextHolder.setDBType("default");
    }

    public static void dynamicDBSource(DBManage dbManage) {
        DruidDataSource dynamicDataSource = new DruidDataSource();
        dynamicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dynamicDataSource.setUrl("jdbc:mysql://" + dbManage.getDbHost() + ":" + dbManage.getDbPort() + "/" + dbManage.getDbSchemaName() + "?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
        dynamicDataSource.setUsername(dbManage.getDbUsername());
        dynamicDataSource.setPassword(dbManage.getDbPassword());

        // 创建动态数据源
        Map<Object, Object> dataSourceMap = DynamicDataSource.getInstance().getDataSourceMap();
        dataSourceMap.put("dynamic-slave", dynamicDataSource);
        DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);

        // 切换为动态数据源实例
        DataSourceContextHolder.setDBType("dynamic-slave");

    }
}
