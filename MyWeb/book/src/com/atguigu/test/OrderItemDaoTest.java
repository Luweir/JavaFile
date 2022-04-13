package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImple;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImple();
        orderItemDao.saveOrderItem(new OrderItem(null, "java", 1, new BigDecimal(200), new BigDecimal(200), "123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaScript", 3, new BigDecimal(100), new BigDecimal(300), "123456"));
    }
}