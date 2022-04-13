package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    /**
     * key 是商品编号，唯一，value是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();


    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // 如果购物车已有该商品，在数量上加1，如果没有该商品，再加入该商品条目
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            // 之前没添加过
            items.put(cartItem.getId(), cartItem);
        } else {
            // 以前添加过
            // 数量累加
            item.setCount(item.getCount() + 1);
            // 更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 更新购物车 id商品的数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        // 先看购物车有没有，如果有修改商品数量更新总金额
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            // 更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除某件商品，不管他有多少件
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 获得总数量
     *
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    // setTotalCount 和 setTotalPrice 没必要存在，因为都是累加

    public Cart() {
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


}
