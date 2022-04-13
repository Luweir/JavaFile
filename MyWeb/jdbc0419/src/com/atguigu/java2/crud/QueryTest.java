package com.atguigu.java2.crud;

import com.atguigu.java1.util.JDBCUtils;
import com.atguigu.java2.bean.Customers;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class QueryTest {
    @Test
    public void test() throws Exception {
        Connection connection = null;
        try {
            // 1 获取数据库连接
            connection = JDBCUtils.getConnection();
            // 2 提供一条 带查询的sql语句
            String sql = "select * from customers where id > ?";
            // 3 创建QueryRunner 实例
            QueryRunner queryRunner = new QueryRunner();
            // 4 通过QueryRunner 实例 调用query()
            BeanListHandler<Customers> beanListHandler = new BeanListHandler(Customers.class);
            List<Customers> customersList = queryRunner.query(connection, sql, beanListHandler, 1);
            customersList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5 关闭连接
            JDBCUtils.Close(connection);
        }
    }

    @Test
    public void test2() throws Exception {
        Connection connection = null;
        try {
            // 1 获取数据库连接
            connection = JDBCUtils.getConnection();
            // 2 提供一条 带查询的sql语句
            String sql = "select * from customers where id = ?";
            // 3 创建QueryRunner 实例
            QueryRunner queryRunner = new QueryRunner();
            // 4 通过QueryRunner 实例 调用query()
            MapHandler mapHandler = new MapHandler();
            Map<String, Object> map = queryRunner.query(connection, sql, mapHandler, 1);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5 关闭连接
            JDBCUtils.Close(connection);
        }
    }
    // output:{name=汪峰, birth=2010-02-02, photo=null, id=1, email=wf@126.com}
    
    @Test
    public void test3() throws Exception {
        Connection connection = null;
        try {
            // 1 获取数据库连接
            connection = JDBCUtils.getConnection();
            // 2 提供一条 带查询的sql语句
            String sql = "select * from customers where id > ?";
            // 3 创建QueryRunner 实例
            QueryRunner queryRunner = new QueryRunner();
            // 4 通过QueryRunner 实例 调用query()
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> map = queryRunner.query(connection, sql, mapListHandler, 1);
            map.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5 关闭连接
            JDBCUtils.Close(connection);
        }
    }
    /* output
    {name=王菲, birth=1988-12-26, photo=null, id=2, email=wangf@163.com}
    {name=林志玲, birth=1984-06-12, photo=null, id=3, email=linzl@gmail.com}
    {name=汤唯, birth=1986-06-13, photo=null, id=4, email=tangw@sina.com}
    {name=成龙, birth=1955-07-14, photo=null, id=5, email=Jackey@gmai.com}
    {name=迪丽热巴, birth=1983-05-17, photo=null, id=6, email=reba@163.com}
    {name=刘亦菲, birth=1991-11-14, photo=null, id=7, email=liuyifei@qq.com}
    {name=陈道明, birth=2014-01-17, photo=null, id=8, email=bdf@126.com}
    {name=周杰伦, birth=1979-11-15, photo=null, id=10, email=zhoujl@sina.com}
    {name=黎明, birth=1998-09-08, photo=null, id=12, email=LiM@126.com}
    {name=张学友, birth=1998-12-21, photo=null, id=13, email=zhangxy@126.com}
    {name=朱茵, birth=2014-01-16, photo=[B@11bd0f3b, id=16, email=zhuyin@126.com}
    {name=贝多芬, birth=2014-01-17, photo=null, id=18, email=beidf@126.com}
    {name=Tom, birth=2020-09-08, photo=null, id=19, email=111111@163.com}
     */
}
