package com.atguigu.java1.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCUtils {
    // 只有一个池子
    private static DataSource dataSource;

    /**
     * 获取数据库连接的方法
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        // 通过数据源获取连接并返回
        Connection connection = dataSource.getConnection();

        return connection;
    }

    static {
        // 提供properties，并加载指定配置文件的流
        try {
            Properties pro = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            // 通过DruidDataSourceFactory创建一个数据源
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
