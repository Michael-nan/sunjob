package com.servlet;

import com.dao.UserDao;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    private UserDao userDao=new UserDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String p=request.getParameter("p");

        if ("check".equals(p)) {
            doCheck(request,response);
        }
    }

    private void doCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo userInfo = userDao.check(username, password);

        if (userInfo != null) {
            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);

            usernameCookie.setMaxAge(100000);
            passwordCookie.setMaxAge(100000);

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

            request.getRequestDispatcher("main.jsp").forward(request, response);

        } else {
            response.getWriter().println("<script>alert('用户名或密码错误！');location='index.jsp';</script>");
        }
    }
}
