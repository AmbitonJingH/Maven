package com.atguigu.imperial.court.dao;
/*
 * @author  AmbitionJingH
 * @date  2023/8/12 23:25
 * @version 1.0
 */

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    public List<T> getBeanList(String sql,Class<T> pojoClass,Object... params){
        try {
            Connection connection = JDBCUtils.getConnection();

            List<T> queryList = queryRunner.query(connection, sql, new BeanListHandler<>(pojoClass), params);
            return queryList;
        } catch (SQLException e) {
            e.printStackTrace();
            new RuntimeException(e);
        }
        return null;
    }
    public T getSingleBean(String sql,Class<T> pojoClass,Object... params){
        try {
            Connection connection = JDBCUtils.getConnection();

            T pojo = queryRunner.query(connection, sql, new BeanHandler<>(pojoClass), params);
            return pojo;
        } catch (SQLException e) {
            e.printStackTrace();
            new RuntimeException(e);
        }
        return null;
    }

    public int update(String sql,Object... params){

        try {
            Connection connection = JDBCUtils.getConnection();
            int rows = queryRunner.update(connection, sql, params);
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            new RuntimeException(e);
            return 0;
        }

    }
}
