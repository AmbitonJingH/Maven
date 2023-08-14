package com.atguigu.imperial.court.filter;
/*
 * @author  AmbitionJingH
 * @date  2023/8/13 10:22
 * @version 1.0
 */

import com.atguigu.imperial.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TransactionFilter implements Filter {
    private static Set<String> staticResourceExtNameSet;
    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //前置操作排除静态资源
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String servletPath = httpServletRequest.getServletPath();
        String extName = servletPath.substring(servletPath.lastIndexOf("."));
        if(staticResourceExtNameSet.contains(extName)){
            //检测到如果为静态资源则直接放行，不做事务操作
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        Connection connection = null;
        try {
            //1.获取数据库连接
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            //2.核心操作
            filterChain.doFilter(servletRequest, servletResponse);
            //3.提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                //4.事务回滚
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //页面显示：将捕获到的异常发送到指定页面
            //获取异常信息
            String message = e.getMessage();
            servletRequest.setAttribute("systemMessage",message);
            servletRequest.getRequestDispatcher("/").forward(servletRequest,servletResponse);


        } finally {
            //5.释放连接
            JDBCUtils.releaseConnection(connection);
        }
    }

    @Override
    public void destroy() {

    }
}
