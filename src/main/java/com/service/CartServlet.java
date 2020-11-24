package com.service;

import com.bean.Cart;
import com.dao.CartDao;
import com.dao.LogDao;
import com.bean.User;
import com.dao.UserDAo;
import com.dao.Sendemail;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("mycart")) {
            CartDao cartdao = new CartDao();
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartdao.showCart(user);
            request.setAttribute("cart", cart_list);
            String sum_price = cartdao.Getprice(user);
            request.getRequestDispatcher("cart.jsp?username=" + user + "&price=" + sum_price).forward(request,response);
        }
        if (action.equals("addCart")) {
            String id = request.getParameter("id");
            String price = request.getParameter("price");
            String num = request.getParameter("number");
            int number = Integer.parseInt(num);
            String provider = request.getParameter("provider");
            String name = request.getParameter("name");
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            Cart cart = new Cart();
            cart.Setid(id);
            cart.Setname(name);
            cart.Setprice(price);
            cart.Setprovider(provider);
            cart.Setuser(user);
            cart.Setnumber(number);
            CartDao cartdao = new CartDao();
            cartdao.addcart(cart);
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartdao.showCart(user);
            request.setAttribute("cart", cart_list);
            String sum_price = cartdao.Getprice(user);
            request.getRequestDispatcher("cart.jsp?username=" + user + "&price=" + sum_price).forward(request,
                    response);
        }
        if (action.equals("gotoUpdate")) {
            CartDao cartDao = new CartDao();
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            Cart cart = new Cart();
            cart = cartDao.gotoupdate(id, user);
            request.setAttribute("gotoupdate", cart);
            String sum_price = cartDao.Getprice(user);
            request.getRequestDispatcher("cart.jsp?username=" + user + "&price=" + sum_price).forward(request,
                    response);
        }
        if (action.equals("update")) {
            CartDao cartDao = new CartDao();
            Cart cart = new Cart();
            String id = request.getParameter("id");
            String num = request.getParameter("number");
            int number = Integer.parseInt(num);
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            cart.Setid(id);
            cart.Setnumber(number);
            cart.Setuser(user);
            cartDao.update(cart);
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartDao.showCart(user);
            request.setAttribute("cart", cart_list);
            String sum_price = cartDao.Getprice(user);
            request.getRequestDispatcher("cart.jsp?username=" + user + "&price=" + sum_price).forward(request,
                    response);
        }
        if (action.equals("delete")) {
            CartDao cartDao = new CartDao();
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            cartDao.delete(id, user);
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartDao.showCart(user);
            request.setAttribute("cart", cart_list);
            String sum_price = cartDao.Getprice(user);
            request.getRequestDispatcher("cart.jsp?username=" + user + "&price=" + sum_price).forward(request,
                    response);
        }
        if (action.equals("purchase")) {
            CartDao cartDao = new CartDao();
            LogDao logDao = new LogDao();
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            List<Cart> cart_list = new ArrayList<>();
            cart_list = cartDao.showCart(user);
            if (cart_list != null)
                for (Cart p : cart_list)
                    logDao.addlog(p, "购买");
            cartDao.deleteall(user);
            UserDAo userDao = new UserDAo();
            User u = userDao.exist(user);
            Sendemail send=new Sendemail();
            try{
            send.sendmail(u.Getemail());
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            cart_list = new ArrayList<>();
            cart_list = cartDao.showCart(user);
            request.setAttribute("cart", cart_list);
            String sum_price = cartDao.Getprice(user);
            request.getRequestDispatcher("cart.jsp?username=" + user + "&price=" + sum_price).forward(request,
                    response);
        }
    }

}
