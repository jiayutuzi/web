package com.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "LoginServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logout(request,response);
    }
    public void Logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession hs = req.getSession();
		hs.invalidate();
		resp.sendRedirect("Login.jsp");
	}
}
