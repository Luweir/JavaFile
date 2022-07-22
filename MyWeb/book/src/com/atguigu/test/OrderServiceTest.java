package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CartItem(1, "java", 1, new BigDecimal(200), new BigDecimal(200)));
        cart.addItem(new CartItem(2, "cpp", 1, new BigDecimal(500), new BigDecimal(500)));

        OrderService orderService = new OrderServiceImpl();
        String orderId = orderService.createOrder(cart, 1);
        System.out.println("订单号是：" + orderId);
    }
}