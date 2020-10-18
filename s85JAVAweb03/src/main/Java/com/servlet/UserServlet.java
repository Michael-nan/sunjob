package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String p=request.getParameter("p");
        if ("regist".equals(p)){
            doRegist(request,response);
        }


    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username =request.getParameter("username");
        String password =request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","root","root");
            String sql="select username ,password from userinfo where username = ? and password = ? ";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                System.out.println(username);
                System.out.println(password);
                response.getWriter().println("<script>alert('登录成功!');</script>");
            }else{
                response.getWriter().println("<script>alert('登录失败!');</script>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] lovers =request.getParameterValues("lovers");
        if (lovers!=null){
            for (String love:lovers) {
                System.out.println(love);
            }
        }
        response.getWriter().println("<script>alert('注册成功!');</script>");
        request.getRequestDispatcher("show.jsp").forward(request,response);
    }
}
