package com.servlet;

import com.dao.DepDao;
import com.pojo.Dep;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dep.do")
public class DepServlet extends HttpServlet {
    private DepDao depDao=new DepDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml;charset=utf-8");
        String p=request.getParameter("p");

        if ("findall".equals(p)) {
            doFindAll(request,response);
        }

    }

    private void doFindAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Dep> list=depDao.findAll();

        if (list.size()==0) {
            response.getWriter().println("[]");
            return;
        }

        StringBuffer sb=new StringBuffer("");
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        sb.append("<deps>");
        for (Dep dep : list) {
            sb.append("<dep depid='").append(dep.getDepid()).append("' depname='").append(dep.getDepname()).append("'>").append("</dep>");
        }
        sb.append("</deps>");
        System.out.println(sb.toString());
        response.getWriter().println(sb.toString());
    }
}
