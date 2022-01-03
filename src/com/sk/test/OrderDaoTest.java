package com.sk.test;

import com.sk.dao.OrderDao;
import com.sk.dao.impl.OrderDaoImpl;
import com.sk.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {
    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234657890", new Date(), new BigDecimal(100), 0, 1));

    }


}