package com.servlet;

import com.dao.GoodsDao;
import com.pojo.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
    private GoodsDao goodsDao=new GoodsDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String p=request.getParameter("p");

        if ("fenye".equals(p)) {
            doFenYe(request,response);
        }
    }

    private void doFenYe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page=1;
        int size=1;

        String pageString=request.getParameter("page");
        if (pageString!=null&&pageString.trim().length()>0) {
            page=Integer.parseInt(pageString);
        }

        String sizeString=request.getParameter("size");
        if (sizeString!=null&&sizeString.trim().length()>0) {
            size=Integer.parseInt(sizeString);
        }

        if (page<1) {
            page=1;
        }

        String goodsname=request.getParameter("goodsname");
        String bottom=request.getParameter("bottom");
        String top=request.getParameter("top");

        int count=goodsDao.getCount(goodsname,bottom,top);
        int pageCount=count%size==0?count/size:count/size+1;

        if (page>pageCount) {
            page=pageCount;
        }

        List<Goods> list=goodsDao.fenYe(page,size,goodsname,bottom,top);
        Map map=new HashMap();
        map.put("list",list);
        map.put("page",page);
        map.put("size",size);
        map.put("pageCount",pageCount);
        map.put("goodsname",goodsname);
        map.put("bottom",bottom);
        map.put("top",top);
        map.put("count",count);
        request.setAttribute("map",map);
        request.getRequestDispatcher("WEB-INF/jsp/fenye.jsp").forward(request,response);

    }
}
