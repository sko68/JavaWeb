package com.sk.test;

import com.sk.pojo.Cart;
import com.sk.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "书局结构与算法", 2, new BigDecimal(300), new BigDecimal(300)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "书局结构与算法", 2, new BigDecimal(300), new BigDecimal(300)));
        cart.deleteItem(1);
        System.out.println(cart);

    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "书局结构与算法", 2, new BigDecimal(300), new BigDecimal(300)));
        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.addItem(new CartItem(2, "书局结构与算法", 2, new BigDecimal(300), new BigDecimal(300)));
        cart.clear();

        cart.addItem(new CartItem(1, "Java从入门到入土", 1, new BigDecimal(30), new BigDecimal(30)));
        cart.updateCount(1,10);

        System.out.println(cart);
    }
}