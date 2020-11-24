package com.service;

import com.bean.User;
import com.dao.UserDAo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request,response);
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
    protected  void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        //PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User();
        UserDAo userDAo = new UserDAo();
        user=userDAo.exist(username);
        if(user!=null)
        {
            request.getRequestDispatcher("defeat.jsp?error=no").forward(request,response);
        }
        else
        {
            user = new User();
            user.Setname(username);
            user.Setpassword(password);
            user.Setemail(email);
            userDAo.addUser(user);
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }
}