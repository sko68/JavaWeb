package com.sk.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/3 21:51
 * @Version 1.0
 */


public class JdbcUtils {

    private static DruidDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     *
     * @return  如果返回null说明获取连接成功，否则才表示获取连接成功
     */
    public static java.sql.Connection getConnection() {
        java.sql.Connection conn= null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭连接，放回数据库连接池
     *
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {

            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
