package com.atguigu.java2.crud;

import com.atguigu.java1.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * 测试向数据表中添加、删除、修改数据
 */
public class UpdateTest {
    @Test
    public void test() throws Exception {
        Connection connection = null;
        try {
            // 向数据表中添加一条数据
            // 1. 获取连接
            connection = JDBCUtils.getConnection();
            // 2. 提供一个添加操作的 sql 语句
            String sql = "insert into customers(name,email,birth)values('王海','tom@163.com','2020-09-08')";
            // 3. 使用提供好的api QueryRunner 调用 update() 方法，实现数据的插入
            QueryRunner queryRunner = new QueryRunner();
            // count 添加了几条记录
            int count = queryRunner.update(connection, sql);
            System.out.println("添加了" + count + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的管理，确保这个必须要执行，所以不能用throws Exception，要用try-catch-finally
            JDBCUtils.Close(connection);
        }
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = null;
        try {
            // 向数据表中添加一条数据
            // 1. 获取连接
            connection = JDBCUtils.getConnection();
            // 2. 提供一个添加操作的 sql 语句
            // ?就是占位符，需要我们在 update 里面把问号具体的值 作为参数传入
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            // 3. 使用提供好的api QueryRunner 调用 update() 方法，实现数据的插入
            QueryRunner queryRunner = new QueryRunner();
            // count 添加了几条记录
            // new Date(毫秒数)  可以转换成 date类型，也可以直接输入对于的日期类型
            int count = queryRunner.update(connection, sql, "jack", "4630@qq.com", new Date(31432532323L));
            System.out.println("添加了" + count + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的管理，确保这个必须要执行，所以不能用throws Exception，要用try-catch-finally
            JDBCUtils.Close(connection);
        }
    }

    @Test
    public void test3() throws SQLException {
        Connection connection = null;
        try {
            // 向数据表中添加一条数据
            // 1. 获取连接
            connection = JDBCUtils.getConnection();
            // 2. 提供一个添加操作的 sql 语句
            // ?就是占位符，需要我们在 update 里面把问号具体的值 作为参数传入
            String sql = "delete from customers where id > ?";
            // 3. 使用提供好的api QueryRunner 调用 update() 方法，实现数据的插入
            QueryRunner queryRunner = new QueryRunner();
            // count 添加了几条记录
            // new Date(毫秒数)  可以转换成 date类型，也可以直接输入对于的日期类型
            int count = queryRunner.update(connection, sql, 19);
            System.out.println("删除了" + count + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的管理，确保这个必须要执行，所以不能用throws Exception，要用try-catch-finally
            JDBCUtils.Close(connection);
        }
    }

    @Test
    public void test4() throws SQLException {
        Connection connection = null;
        try {
            // 向数据表中添加一条数据
            // 1. 获取连接
            connection = JDBCUtils.getConnection();
            // 2. 提供一个添加操作的 sql 语句
            // ?就是占位符，需要我们在 update 里面把问号具体的值 作为参数传入
            String sql = "update customers set email = ? where id = ?";
            // 3. 使用提供好的api QueryRunner 调用 update() 方法，实现数据的插入
            QueryRunner queryRunner = new QueryRunner();
            // count 添加了几条记录
            // new Date(毫秒数)  可以转换成 date类型，也可以直接输入对于的日期类型
            int count = queryRunner.update(connection, sql, "111111@163.com", 19);
            System.out.println("修改了" + count + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 资源的管理，确保这个必须要执行，所以不能用throws Exception，要用try-catch-finally
            JDBCUtils.Close(connection);
        }
    }

}
