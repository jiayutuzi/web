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
import java.text.DecimalFormat;  
import com.dao.LogDao;
import com.bean.Log;


@WebServlet(name="LogServlet")
public class LogServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action=request.getParameter("action");
        if(action.equals("show"))
        {
            LogDao logDao=new LogDao();
            List<Log> log_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            log_list=logDao.showlog(username);
            request.setAttribute("log", log_list);
            request.getRequestDispatcher("log.jsp?username="+username).forward(request,response);
        }
        if(action.equals("delete"))
        {
            LogDao logDao=new LogDao();
            String time=request.getParameter("time");
            HttpSession session=request.getSession();
            String user=(String)session.getAttribute("user");
            logDao.delete(time);
            List<Log> log_list=new ArrayList<>();
            log_list=logDao.showlog(user);
            request.setAttribute("log", log_list);
            request.getRequestDispatcher("log.jsp?username="+user).forward(request,response);
        }
        if(action.equals("profit"))
        {
            LogDao logDao=new LogDao();
            List<Log> log_list=new ArrayList<>();
            HttpSession session=request.getSession();
            String username=(String)session.getAttribute("user");
            log_list=logDao.showlog(username);
            int number=0;
            double profit=0;
            for(int i=0;i<log_list.size();i++)
            {
                if(log_list.get(i).Getstatus().equals("浏览"))
                {
                    log_list.remove(i);
                    i--;
                }
                else
                {
                    number+=log_list.get(i).Getnumber();
                    double price=Double.parseDouble(log_list.get(i).Getprice());
                    profit+=price*log_list.get(i).Getnumber();
                }    
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            String sum_price=df.format(profit);
            String num=String.valueOf(number);
            request.setAttribute("profit", log_list);
            request.getRequestDispatcher("profit.jsp?username=" + username + "&price=" + sum_price + "&number="+num).forward(request,response);

        }
    }
}
