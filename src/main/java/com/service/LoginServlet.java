package com.service;

import com.bean.User;
import com.dao.UserDAo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAo userDAo = new UserDAo();
        User user = userDAo.login(username,password);
        if(user != null){
            HttpSession hs = request.getSession();
            hs.setAttribute("user", username);
            request.getRequestDispatcher("ShowServlet").forward(request,response);
        }
        else {
            response.sendRedirect("defeat.jsp?error=yes");
        }
    }
   
}