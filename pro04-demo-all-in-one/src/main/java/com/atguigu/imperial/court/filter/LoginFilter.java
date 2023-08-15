package com.atguigu.imperial.court.filter;
/*
 * @author  AmbitionJingH
 * @date  2023/8/15 11:58
 * @version 1.0
 */

import com.atguigu.imperial.court.util.ImperialCourtConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取session对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        //尝试从session域获取已登录的对象
        Object loginEmp = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);
        //判断loginEmp是否为空
        if(loginEmp != null){
            filterChain.doFilter(request,servletResponse);
            return;
        }
        //为空则回到登录页面
        request.setAttribute("systemMessage",ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        request.getRequestDispatcher("/").forward(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
