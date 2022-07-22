package com.atguigu.java3;

import com.atguigu.java1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomrDaoTest {
    @Test
    public void test() throws SQLException {
        Connection connection = null;
        try {
            CustomerDaoImpl impl = new CustomerDaoImpl();

            connection = JDBCUtils.getConnection();
            List<Customer> all = impl.getAll(connection);
            for (Customer customer : all) {
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.Close(connection);
        }
    }
}
