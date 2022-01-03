package com.sk.test;

import com.sk.pojo.Cart;
import com.sk.pojo.CartItem;
import com.sk.service.OrderService;
import com.sk.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "书局结构与算法", 1, new BigDecimal(300), new BigDecimal(300)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是："+orderService.createOrder(cart, 1));

    }
}