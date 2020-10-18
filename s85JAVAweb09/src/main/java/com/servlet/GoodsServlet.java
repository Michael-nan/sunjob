package com.servlet;

import com.dao.GoodsDao;
import com.dao.PictureDao;
import com.pojo.Goods;
import com.pojo.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
    private GoodsDao goodsDao=new GoodsDao();
    private PictureDao pictureDao =new PictureDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p=request.getParameter("p");
        if ("fenye".equals(p)){
            doFenye(request,response);
        }
        if ("findbyid".equals(p)) {
            doFindbyid(request,response);
        }
        if ("addCar".equals(p)) {
            doAddcar(request,response);
        }
        if ("deletefromcar".equals(p)) {
            doDeletefromcar(request,response);
        }
        if ("deletefromcarajax".equals(p)) {
            doDeletefromcarajax(request,response);
        }
        if ("carnumjian".equals(p)) {
            doCarnumjian(request,response);
        }
        if ("carnumjia".equals(p)) {
            doCarnumjia(request,response);
        }
    }

    private void doCarnumjia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid=request.getParameter("goodsid");
        HttpSession session=request.getSession();
        Goods goods=goodsDao.findbyid(goodsid);
        Map<Goods,Integer> map = (Map<Goods, Integer>) session.getAttribute("map");
        int n=map.put(goods,map.get(goods)+1);
        if (n>0) {
            response.getWriter().println("1");
        }else {
            response.getWriter().println("2");
        }
    }

    private void doCarnumjian(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid=request.getParameter("goodsid");
        HttpSession session=request.getSession();
        Goods goods=goodsDao.findbyid(goodsid);
        Map<Goods,Integer> map= (Map<Goods, Integer>) session.getAttribute("map");
        int n=map.put(goods,map.get(goods)-1) ;
        if (n>0) {
            response.getWriter().println("1");
        }else {
            response.getWriter().println("2");
        }
    }

    private void doDeletefromcarajax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid=request.getParameter("goodsid");
        HttpSession session=request.getSession();
        Goods goods=goodsDao.findbyid(goodsid);
        Map<Goods,Integer> map= (Map<Goods, Integer>) session.getAttribute("map");
        int n=map.remove(goods);
        if (n>0) {
            response.getWriter().println("1");
        }else {
            response.getWriter().println("2");

        }
    }

    private void doDeletefromcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
       HttpSession session=request.getSession();
       Map<Goods,Integer> map= (Map<Goods, Integer>) session.getAttribute("map");
        if (map==null) {
            response.sendRedirect("showCar.jsp");
            return;
        }
        String goodsid=request.getParameter("goodsid");
        Goods goods=goodsDao.findbyid(goodsid);
        map.remove(goods);
        response.sendRedirect("showCar.jsp");

    }

    private void doAddcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid=request.getParameter("goodsid");
        String num=request.getParameter("num");

        HttpSession session=request.getSession();  //得到session

        //先判断该用户有没有购物车
        Map<Goods,Integer> map= (Map<Goods, Integer>) session.getAttribute("map");
        if(map==null){
            map= new HashMap<Goods, Integer>();
        }
        Goods goods=goodsDao.findbyid(goodsid);
        Integer integer=map.get(goods);//判断购物车是否存在该商品
        if (integer==null) {
            map.put(goods,Integer.parseInt(num));
        }else {
            map.put(goods,integer+Integer.parseInt(num));
        }
        session.setAttribute("map",map);
        response.sendRedirect("showCar.jsp");


    }

    private void doFindbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsid=request.getParameter("goodsid");
        Goods goods= goodsDao.findbyid(goodsid);
        List<Picture> list=pictureDao.findbyid(goodsid);
        request.setAttribute("list",list);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("showDetail.jsp").forward(request,response);
    }

    private void doFenye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page=1;
        int size=4;

        String pageString=request.getParameter("page");
        if (pageString!=null&&pageString.trim().length()>0){
            page=Integer.parseInt(pageString);
        }
        String sizeString=request.getParameter("size");
        if (sizeString!=null&&sizeString.trim().length()>0) {
            size=Integer.parseInt(sizeString);
        }
        if (page<1){
            page=1;
        }

        int count=goodsDao.getCount();
        int pageCount=count%size==0?count/size:count/size+1;
        if (page>pageCount){
            page=pageCount;
        }
        List<Goods> list=goodsDao.fenye(page,size);
        Map map=new HashMap();
        map.put("page",page);
        map.put("size",size);
        map.put("count",count);
        map.put("pageCount",pageCount);
        map.put("list",list);
        request.setAttribute("map",map);
        request.getRequestDispatcher("show.jsp").forward(request,response);
    }
}
