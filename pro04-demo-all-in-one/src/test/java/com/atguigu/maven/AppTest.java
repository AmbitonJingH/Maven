package com.atguigu.maven;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.pojo.Emp;
import com.atguigu.imperial.court.util.JDBCUtils;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private BaseDao<Emp> baseDao = new BaseDao<>();
    @Test
    public void test(){
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where emp_id=?";
        Emp singleBean = baseDao.getSingleBean(sql,Emp.class,1);
        System.out.println("singleBean = " + singleBean);
    }
    @Test
    public void test1(){
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp";
        List<Emp> beanList = baseDao.getBeanList(sql, Emp.class);
        for(Emp emp:beanList){
            System.out.println("emp = " + emp);
        }
    }
    @Test
    public void test2(){
        String sql="update t_emp set emp_position=? where emp_id=?";
        int ok = baseDao.update(sql, "minister", "2");
        System.out.println("ok = " + ok);
    }
    @Test
    public void testGetConnection(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection = " + connection);
        JDBCUtils.releaseConnection(connection);
    }

}
