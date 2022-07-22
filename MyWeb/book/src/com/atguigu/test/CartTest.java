package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "book1", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "book2", 1, new BigDecimal(2000), new BigDecimal(2000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "book1", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "book2", 1, new BigDecimal(2000), new BigDecimal(2000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.deleteItem(3);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "book1", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "book2", 1, new BigDecimal(2000), new BigDecimal(2000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.deleteItem(3);
        cart.clear();
        cart.addItem(new CartItem(1, "book1", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.updateCount(1, 10);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "book1", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "book2", 1, new BigDecimal(2000), new BigDecimal(2000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.addItem(new CartItem(3, "book3", 1, new BigDecimal(3000), new BigDecimal(3000)));
        cart.deleteItem(3);
        System.out.println(cart);
    }
}