package com.atguigu.java3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * 针对于Customer表，具体要做哪些操作
 */
public interface CustomerDao {
    /**
     * 向数据表中添加记录
     *
     * @param connection 数据库的连接
     * @param cust       要添加到 数据库表 中的Customer 对象
     * @return 返回添加几条记录
     */
    int addCustomer(Connection connection, Customer cust) throws SQLException;

    /**
     * 根据指定的id，删除一条记录
     *
     * @param connection 连接
     * @param id         要删除的数据对应的ID
     */
    int deleteById(Connection connection, int id) throws SQLException;

    /**
     * 修改表中的一条记录
     * Customer customer=new Customer(10,"张哲","123123@123.com","new Date(21313L)");
     *
     * @param connection
     * @param customer
     */
    int updateCustomer(Connection connection, Customer customer) throws SQLException;

    /**
     * 根据指定 id 查询表中一条记录
     *
     * @param connection
     * @param id
     * @return 要查询的 id 的对象
     */
    Customer getCustomer(Connection connection, int id) throws SQLException;


    // 具体的需求 根据前端页面的功能来添加

    /**
     * 查询表中所有记录
     *
     * @param connection
     * @return
     */
    List<Customer> getAll(Connection connection) throws SQLException;

    /**
     * 查询表中条目数
     *
     * @param connection
     * @return
     */
    long getCount(Connection connection) throws SQLException;

    /**
     * 查询表中最大的生日
     *
     * @param connection
     * @return
     */
    Date getMax(Connection connection) throws SQLException;


}
