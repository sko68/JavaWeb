package com.sk.service;

import com.sk.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

}
