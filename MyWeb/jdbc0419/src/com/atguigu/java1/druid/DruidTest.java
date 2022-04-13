package com.atguigu.java1.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DruidTest {
    @Test
    public void test1() throws SQLException {
        // DataSource 数据源：包含连接池和连接池管理两部分, 就是池子
        DruidDataSource source = new DruidDataSource();
        // 设置基本信息
        source.setUsername("root");
        source.setPassword("1234");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setDriverClassName("com.mysql.jdbc.Driver");

        // 得到一个连接 返回
        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2() {
        try {
            // 提供properties，并加载指定配置文件的流
            Properties pro = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            // 通过DruidDataSourceFactory创建一个数据源
            DataSource source = DruidDataSourceFactory.createDataSource(pro);

            // 获取连接并返回
            Connection connection = source.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
