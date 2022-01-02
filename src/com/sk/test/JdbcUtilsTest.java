package com.sk.test;

import com.sk.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 7:41
 * @Version 1.0
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils() {
        for (int i = 0; i < 100; i++) {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
        JdbcUtils.close(connection);

        }

    }
}
