package com.atguigu.imperial.court.util;
/*
 * @author  AmbitionJingH
 * @date  2023/8/12 20:17
 * @version 1.0
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
* 功能1：从数据源获取数据库连接
* 功能2：将数据源绑定到本地线程（ThreadLocal）
* 功能3：释放线程和本地线程解除绑定
* */
public class JDBCUtils {
    // 由于 ThreadLocal 对象需要作为绑定数据时 k-v 对中的 key，所以要保证唯一性
    // 加 static 声明为静态资源即可保证唯一性
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static DataSource dataSource;

    static {

        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = threadLocal.get();
            if(connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void releaseConnection(Connection connection){
        if(connection!= null){
            try {
                connection.close();
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }





}
