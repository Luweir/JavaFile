package com.atguigu.dao.impl;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectJoin;
import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    // 使用DbUtils 操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行insert、update、delete语句
     *
     * @return -1表示失败，其他值表示操作的行数
     */
    public int update(String sql, Object... objs) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, objs);
        } catch (SQLException e) {
            e.printStackTrace();
            // 把异常往外面抛，这样外面才能捕获
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回单条查询对象
     *
     * @param sql
     * @param clazz
     * @param objs
     * @param <T>
     * @return
     */
    public <T> T queryForOne(String sql, Class<T> clazz, Object... objs) {
        Connection connection = JdbcUtils.getConnection();
        try {
            BeanHandler<T> handler = new BeanHandler<T>(clazz);
            T instance = queryRunner.query(connection, sql, objs, handler);
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
            // 把异常往外面抛，这样外面才能捕获
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多条记录返回 list
     *
     * @param sql
     * @param clazz
     * @param objs
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> List<T> queryForList(String sql, Class<T> clazz, Object... objs) {
        BeanListHandler<T> handler = new BeanListHandler<>(clazz);
        Connection connection = JdbcUtils.getConnection();
        try {
            List<T> instances = queryRunner.query(connection, sql, handler, objs);
            return instances;
        } catch (SQLException e) {
            e.printStackTrace();
            // 把异常往外面抛，这样外面才能捕获
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回对某一列的特殊值，比如最大值、最小值
     *
     * @param sql
     * @param objs
     * @param <T>
     * @return
     */
    public <T> T getSpecialValues(String sql, Object... objs) {
        Connection connection = JdbcUtils.getConnection();
        try {
            ScalarHandler scalarHandler = new ScalarHandler();
            Object value = queryRunner.query(connection, sql, scalarHandler, objs);
            return (T) value;
        } catch (SQLException e) {
            e.printStackTrace();
            // 把异常往外面抛，这样外面才能捕获
            throw new RuntimeException(e);
        }
    }
}
