package com.servlet;

import com.dao.UserDao;
import com.pojo.Collection;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    private UserDao userDao=new UserDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String p=request.getParameter("p");
        if ("addCollection".equals(p)) {
            doAddCollection(request,response);
        }

        if ("login".equals(p)) {
            doLogin(request,response);
        }

        if ("zhuce".equals(p)) {
            doZhuCe(request,response);
        }

        if ("findbyusername".equals(p)) {
            doFindbyusername(request,response);
        }
    }

    private void doFindbyusername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        List<Collection> list=userDao.findbyusername(username);
        request.setAttribute("list",list);
        request.getRequestDispatcher("collection.jsp").forward(request,response);
    }

    private void doZhuCe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        int n =userDao.zhuce(username,password);
        if (n>0) {
            response.getWriter().println("<script>alert('注册成功！');location='login.jsp'</script>");
        }else {
            response.getWriter().println("<script>alert('该用户名已被注册！');location='zhuce.jsp'</script>");
        }

    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        UserInfo userInfo=userDao.login(username,password);
        if (userInfo == null) {
            response.getWriter().println("<script>alert('用户名或密码错误！');location='login.jsp';</script>");
            return;
        }else {
            request.getSession().setAttribute("userinfo",userInfo);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    private void doAddCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //收藏之前    得先判断用户有没有登录
        HttpSession session=request.getSession();
        UserInfo userInfo= (UserInfo) session.getAttribute("userinfo");
        if (userInfo==null) {
            response.getWriter().println("<script>alert('请先进行登录！');location='login.jsp'</script>");
            return;
        }

        //判断用户是否已经收藏了改商品
        String username=userInfo.getUsername();
        String goodsid=request.getParameter("goodsid");
        Collection collection=userDao.check(username,goodsid);

        if (collection!=null) {
            response.getWriter().println("<script>alert('您已经收藏了该商品！');location='goods.do?p=findbyid&goodsid="+goodsid+"'</script>");
            return;
        }

        int n=userDao.addCollection(username,goodsid);
        if (n>0) {
            response.getWriter().println("<script>alert('收藏成功！');location='goods.do?p=findbyid&goodsid="+goodsid+"'</script>");
        }




    }
}
