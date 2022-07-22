package com.atguigu.java3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    @Override
    public int addCustomer(Connection connection, Customer cust) throws SQLException {
        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        // 实现方法需要抛异常，那么接口方法也要抛异常
        int count = update(connection, sql, cust.getName(), cust.getEmail(), cust.getEmail());
        return count;
    }

    @Override
    public int deleteById(Connection connection, int id) throws SQLException {
        String sql = "delete from customers where id = ?";
        int count = update(connection, sql, id);
        return count;
    }

    @Override
    public int updateCustomer(Connection connection, Customer customer) throws SQLException {
        String sql = "update customers set name=?,email=?,birth=? where id = ?";
        int count = update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
        return count;
    }

    @Override
    public Customer getCustomer(Connection connection, int id) throws SQLException {
        String sql = "select id,name,email,birth from customers where id=?";
        Customer customer = getInstance(connection, sql, Customer.class, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection connection) throws SQLException {
        String sql = "select id,name,email,birth from customers";
        List<Customer> list = getForList(connection, sql, Customer.class);
        return list;
    }

    @Override
    public long getCount(Connection connection) throws SQLException {
        String sql = "select count(*) from customers";
        long count = getValue(connection, sql);
        return count;
    }

    @Override
    public Date getMax(Connection connection) throws SQLException {
        String sql = "select max(birth) from customers";
        Date maxBirth = getValue(connection, sql);
        return maxBirth;
    }
}
