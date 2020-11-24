package com.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.dao.GoodsDao;
import com.bean.Goods;

@WebServlet(name="ShowServlet")
public class ShowServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDao goodsDao=new GoodsDao();
        List<Goods> goods_list=new ArrayList<>();
        goods_list=goodsDao.showgoods();
        request.setAttribute("goods", goods_list);
        HttpSession session=request.getSession();
        String username=(String)session.getAttribute("user");
        request.getRequestDispatcher("product_list.jsp?username="+username).forward(request,response);
    }
}
