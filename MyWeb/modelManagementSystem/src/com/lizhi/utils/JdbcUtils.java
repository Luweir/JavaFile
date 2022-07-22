package com.lizhi.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    /**
     * 保证事务的原子性，线程内所有操作都用这个连接
     */
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回null，说明获取连接失败，有值就是成功
     */
    public static Connection getConnection() {
        // 先从 threadLocal 里面去拿
        Connection connection = conns.get();
        // 如果还没有连接，就从连接池里面取,保证一个线程只用一个connection
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                // 获取后我就保存到 threadLocal对象中，供后面的 jdbc 操作使用
                conns.set(connection);
                // 设置连接的 提交 为手动管理
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return connection;
    }

    /**
     * 提交并关闭连接
     */
    public static void submitAndClose() {
        // 得到连接对象
        Connection connection = conns.get();
        if (connection != null) {
            // 不为null 说明之前使用过连接，操作过数据库
            try {
                // 提交事务
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        // 一定要执行 remove 操作，否则出错，因为 tomcat 服务器底层使用线程池技术
        conns.remove();
    }

    /**
     * 回滚并关闭连接
     */
    public static void rollbackAndClose() {
        // 得到连接对象
        Connection connection = conns.get();
        if (connection != null) {
            // 不为null 说明之前使用过连接，操作过数据库
            try {
                // 回滚事务
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        // 一定要执行 remove 操作，否则出错，因为 tomcat 服务器底层使用线程池技术
        conns.remove();
    }

    /**
     * 关闭连接，放回数据库连接池
     *
     * @param connection
     */
//    public static void close(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    static {
        // 提供properties，并加载指定配置文件的流
        try {
            Properties pro = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(is);
            // 通过DruidDataSourceFactory创建一个数据源
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
