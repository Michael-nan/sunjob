package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test.do")
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // request.setCharacterEncoding("utf-8");
      //  response.setContentType("text/html;charset=utf-8");
        String p=request.getParameter("p");
        if ("check".equals(p)){
            doCheck(request,response);
        }


    }

    private void doCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username=request.getParameter("username");
    if ("sunjob".equals(username)){
        response.getWriter().println("1");
    }else {
        response.getWriter().println("2");
    }
    }
}
