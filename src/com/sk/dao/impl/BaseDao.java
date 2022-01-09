package com.sk.dao.impl;

import com.sk.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 8:26
 * @Version 1.0
 */
public abstract class BaseDao {

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行Insert、update、Delete语句
     * 如果返回-1，表示执行失败
     */

    public int update(String sql,Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type 返回对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean的sql语句
     * @param type 返回对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object ... args) {

        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql,new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
