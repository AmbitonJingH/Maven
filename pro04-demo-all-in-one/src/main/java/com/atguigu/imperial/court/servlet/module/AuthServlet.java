package com.atguigu.imperial.court.servlet.module;
/*
 * @author  AmbitionJingH
 * @date  2023/8/14 17:53
 * @version 1.0
 */

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.exception.LoginFailedException;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.service.impl.EmpServiceImpl;
import com.atguigu.imperial.court.servlet.base.ModelBaseServlet;
import com.atguigu.imperial.court.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends ModelBaseServlet {

    private EmpService empService = new EmpServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获取请求参数
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");
            //2.调用EmpService方法执行登陆逻辑
            Emp emp = empService.getEmpByLoginAccount(loginAccount,loginPassword);
            //3.获取Session
            HttpSession session = request.getSession();
            //4.将Emp对象存入Session域
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME,emp);
            //5.前往指定页面视图
            response.sendRedirect(request.getContextPath()+"/work?method=showMemorialsDigestList");
        } catch (Exception e) {
            if(e instanceof LoginFailedException){
                request.setAttribute("message",e.getMessage());
                processTemplate("index",request,response);
            }else {
                throw new RuntimeException(e);
            }
        }

    }


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过request获得session
        HttpSession session = request.getSession();
        //2.使session失效
        session.invalidate();
        //3.回到首页
        String templateName = "index";
        processTemplate(templateName,request,response);
    }
}
