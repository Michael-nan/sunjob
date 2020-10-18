package com.servlet;

import com.dao.UserDao;
import com.pojo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
     private UserDao userDao=new UserDao();//DAO全局，多个方法需要用
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String p=request.getParameter("p");

      if("fenye".equals(p)){     //如果参数是分页    就做分页的操作
          doFenYe(request,response);
      }
      if ("deletebyusername".equals(p)){
          doDeleteByName(request,response);
      }

    }

    private void doDeleteByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username=request.getParameter("username");
    userDao.deleteByUsername(username);
    doFenYe(request,response);//刷新
    }

    private void doFenYe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page=1;
        int size=3;////如果用户没有指定页数页条数  给默认值
        String pageString=request.getParameter("page");
        if(pageString!=null && pageString.trim().length()>0){
            page=Integer.parseInt(pageString);
        }
        String sizeString=request.getParameter("size");
        if (sizeString!=null && sizeString.trim().length()>0){
            size=Integer.parseInt(sizeString);
        }

        //越界判断
        if (page<1){
            page=1;
        }

        //总页数=总条数(数据库查)/每一页的条数size
       /* Double count=userDao.getCount();
        int pageCount=Math.ceil(count/size);

        */
        int count=userDao.getCount();
        int pageCount= count%size==0 ? count/size: count/size + 1;
        if (page>pageCount){
            page=pageCount;
        }

        List<UserInfo> list=userDao.fenye(page,size);//查询
        Map map=new HashMap();
        map.put("page",page);//当前页
        map.put("size",size);//每一页条数
        map.put("list",list);//该页的数据
        map.put("count",count);//总条数
        map.put("pageCount",pageCount);//总页数
        request.setAttribute("map",map);//存值
        request.getRequestDispatcher("show.jsp").forward(request,response);



    }
}
