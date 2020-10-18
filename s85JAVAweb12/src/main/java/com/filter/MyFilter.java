package com.filter;

import com.dao.UserDao;
import com.pojo.UserInfo;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        UserInfo userInfo= (UserInfo) ((HttpServletRequest)request).getSession().getAttribute("userinfo");
        if (userInfo==null) {
            Cookie[] cookies=((HttpServletRequest) request).getCookies();
            String username=null;
            String password=null;

            if (cookies!=null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        username = cookie.getName();
                    }

                    if ("password".equals(cookie.getValue())) {
                        password = cookie.getValue();
                    }
                }

                if (username!=null&&password!=null) {
                    UserDao userDao = new UserDao();
                    userInfo = userDao.check(username, password);
                    if (userInfo != null) {
                        ((HttpServletResponse)response).sendRedirect("main.jsp");
                    }
                }
            }
        }
        //进了 过滤器  还得出去 回调函数
        filterChain.doFilter(request,response);//过滤
    }

    @Override
    public void destroy() {

    }
}
