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
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

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
     * @return 如果返回null说明获取连接成功，否则才表示获取连接成功
     */
    public static java.sql.Connection getConnection() {

        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();  //从数据库连接池中获取连接

                conns.set(conn);   //保存到ThreadLocal对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false);  //设置为手动管理事务

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事物并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.commit();      //提交事物
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();    //关闭连接释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove(); // 一定要执行remove()操作，否则就会出错(因为TomCat服务器底层是用了线程池技术)

    }

    /**
     * 回滚事物并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.commit();      //回滚事物
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();    //关闭连接释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove(); // 一定要执行remove()操作，否则就会出错(因为TomCat服务器底层是用了线程池技术)
    }

    /**
     * 关闭连接，放回数据库连接池
     *
     * @param conn

    public static void close(Connection conn) {
        if (conn != null) {

            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }*/
}
