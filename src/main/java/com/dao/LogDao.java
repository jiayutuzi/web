package com.dao;
import com.bean.Log;
import com.bean.Cart;
import com.bean.Goods;
import com.utils.JdbcUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
public class LogDao {
    public void addlog(Cart cart,String status)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //获取String类型的时间
        String time = sdf.format(date);
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into log values(?,?,?,?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,time);
            preparedStatement.setString(2,cart.Getid());
            preparedStatement.setString(3,cart.Getname());
            preparedStatement.setString(4,cart.Getprice());
            preparedStatement.setInt(5,cart.Getnumber());
            preparedStatement.setString(6,cart.Getuser());
            preparedStatement.setString(7,cart.Getprovider());
            preparedStatement.setString(8,status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public void addlog(Goods good,String status,String user)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //获取String类型的时间
        String time = sdf.format(date);
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into log values(?,?,?,?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,time);
            preparedStatement.setString(2,good.Getid());
            preparedStatement.setString(3,good.Getname());
            preparedStatement.setString(4,good.Getprice());
            preparedStatement.setInt(5,0);
            preparedStatement.setString(6,user);
            preparedStatement.setString(7,good.Getprovider());
            preparedStatement.setString(8,status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
    public List<Log> showlog(String provider)
    {
        List<Log> log_list=new ArrayList<Log>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from log where provider=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String time=resultSet.getString(1);
                String id=resultSet.getString(2);
                String name=resultSet.getString(3);
                String price=resultSet.getString(4);
                int num=resultSet.getInt(5);
                String user=resultSet.getString(6);
                String status=resultSet.getString(8);
                Log p=new Log(time,id,name,price,num,user,provider,status);
                log_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return log_list;
    }
    public void delete(String time)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from log where time=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, time);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
}
