package com.sk.test;

import com.sk.dao.OrderItemDao;
import com.sk.dao.impl.OrderItemDaoImpl;
import com.sk.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {
    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"Java从入门到精通",1,new BigDecimal(250),new BigDecimal(250),"1234657890"));

    }

}