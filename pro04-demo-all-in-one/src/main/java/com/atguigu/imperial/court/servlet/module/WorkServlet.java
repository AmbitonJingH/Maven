package com.atguigu.imperial.court.servlet.module;
/*
 * @author  AmbitionJingH
 * @date  2023/8/14 23:28
 * @version 1.0
 */

import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;
import com.atguigu.imperial.court.service.impl.MemorialsServiceImpl;
import com.atguigu.imperial.court.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WorkServlet extends ModelBaseServlet {
    private MemorialsService memorialsService = new MemorialsServiceImpl();


    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Memorials> memorialsList = memorialsService.getAllMemorialsDigest();
        request.setAttribute("memorialsList",memorialsList);
        String templateName = "memorials-List";
        processTemplate(templateName,request,response);
    }

    protected void showMemorialsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从请求参数读取memorialsId
        String memorialsId = request.getParameter("memorialsId");
        //2.从service查询memorials对象
        Memorials memorials = memorialsService.getMemorialsById(memorialsId);
        //*************补充功能************
        Integer memorialsStatus = memorials.getMemorialsStatus();
        if(memorialsStatus == 0){
            //更新奏折状态
            memorialsService.updateMemorialsStatusToRead(memorialsId);
            memorials.setMemorialsStatus(1);
        }
        //*************补充功能************
        //3.将memorials保存到请求域
        request.setAttribute("memorials",memorials);
        //4.解析渲染视图
        String templateName = "memorials_detail";
        processTemplate(templateName,request,response);
    }

    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单提交的请求参数
        String memorialsId = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedbackContent");
        //执行更新
        memorialsService.updateMemorialsFeedBack(memorialsId,feedbackContent);
        //重定向会奏折列表
        response.sendRedirect(request.getContextPath()+"/work?method=showMemorialsDigestList");
    }
}
