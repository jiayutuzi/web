package com.service;

import com.bean.Goods;
import com.dao.GoodsDao;
import com.dao.LogDao;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
@WebServlet(name = "GoodsServlet")
public class GoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String add=request.getParameter("addproduct");
        String action=request.getParameter("action");
        if(add!=null)
        {
            Date now=new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String id=dateFormat.format(now);
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String num=request.getParameter("number");
            int number = Integer.parseInt(num);
            //String provider=request.getParameter("provider");
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");
            Goods goods=new Goods();
            GoodsDao goodsDao=new GoodsDao();
            goods.Setid(id);
            goods.Setname(name);
            goods.Setprice(price);
            goods.Setnumber(number);
            goods.Setprovider(provider);
            goodsDao.addGoods(goods);
            request.getRequestDispatcher("ShowServlet").forward(request,response);
        }
        if(action.equals("mygoods"))
        {
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");
            goods_list=goodsDao.showgoods(provider);
            request.setAttribute("goods", goods_list);
            request.getRequestDispatcher("personal.jsp?username="+provider).forward(request,response);
        }
        if(action.equals("gotoUpdate"))
        {
            GoodsDao goodsDao=new GoodsDao();
            String id=request.getParameter("id");
            Goods good=new Goods();
            good=goodsDao.gotoupdate(id);
            request.setAttribute("gotoupdate", good);
            request.getRequestDispatcher("update.jsp").forward(request,response);
        }
        if(action.equals("update"))
        {
            GoodsDao goodsDao=new GoodsDao();
            Goods good=new Goods();
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String price=request.getParameter("price");
            String num=request.getParameter("number");
            int number = Integer.parseInt(num);
            good.Setid(id);
            good.Setname(name);
            good.Setprice(price);
            good.Setnumber(number);
            goodsDao.update(good);
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");
            goods_list=goodsDao.showgoods(provider);
            request.setAttribute("goods", goods_list);
            request.getRequestDispatcher("personal.jsp?username="+provider+"&success=true").forward(request,response);
        }
        if(action.equals("delete"))
        {
            GoodsDao goodsDao=new GoodsDao();
            String id=request.getParameter("id");
            goodsDao.delete(id);
            List<Goods> goods_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String provider=(String)session.getAttribute("user");
            goods_list=goodsDao.showgoods(provider);
            request.setAttribute("goods", goods_list);
            request.getRequestDispatcher("personal.jsp?username="+provider+"&success=true").forward(request,response);
        }
        if(action.equals("showone"))
        {
            GoodsDao goodsDao=new GoodsDao();
            String id=request.getParameter("id");
            Goods good=new Goods();
            good=goodsDao.Showone(id);
            HttpSession session=request.getSession();
            String user=(String)session.getAttribute("user");
            LogDao logDao=new LogDao();
            logDao.addlog(good, "浏览", user);
            request.setAttribute("good", good);
            request.getRequestDispatcher("showone.jsp?username="+user).forward(request,response);
        }
        if(action.equals("search"))
        {
            String name=request.getParameter("product");
            GoodsDao goodsDao=new GoodsDao();
            List<Goods> goods_list=new ArrayList<>();
            goods_list=goodsDao.search(name);
            request.setAttribute("goods", goods_list);
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            request.getRequestDispatcher("product_list.jsp?username="+username).forward(request,response);
        }
    }
   
}
