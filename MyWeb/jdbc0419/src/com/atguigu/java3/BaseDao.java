package com.atguigu.java3;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    //通用的增删改查
    public int update(Connection connection, String sql, Object... objs) throws SQLException {
        int count = queryRunner.update(connection, sql, objs);
        return count;
    }

    // 查询操作 单条记录
    public <T> T getInstance(Connection connection, String sql, Class<T> clazz, Object... objs) throws SQLException {
        BeanHandler<T> handler = new BeanHandler<T>(clazz);
        T instance = queryRunner.query(connection, sql, objs, handler);
        return instance;
    }

    // 查询多条记录
    public <T> List<T> getForList(Connection connection, String sql, Class<T> clazz, Object... objs) throws SQLException {
        BeanListHandler<T> handler = new BeanListHandler<>(clazz);
        List<T> instances = queryRunner.query(connection, sql, handler, objs);
        return instances;
    }

    // 查询特殊值
    public <T> T getValue(Connection connection, String sql, Object... objs) throws SQLException {
        ScalarHandler scalarHandler = new ScalarHandler();
        Object value = queryRunner.query(connection, sql, scalarHandler, objs);
        return (T) value;
    }

}
