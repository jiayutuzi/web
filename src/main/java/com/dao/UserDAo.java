package com.dao;

import com.bean.User;
import com.utils.JdbcUtils;

import java.sql.*;

public class UserDAo {
    public User login(String username, String password){
        User u = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username=? and password=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                u = new User();
                u.Setname(resultSet.getString("username"));
                u.Setpassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }
        finally{
            JdbcUtils.close(preparedStatement,connection);
        }
            return u;
    }

    public User exist(String username){
        User u = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from user where username=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                u = new User();
                u.Setname(resultSet.getString("username"));
                u.Setemail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }
        finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return u;
    }

    public void addUser(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into user values(?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,user.Getname());
            preparedStatement.setString(2,user.Getpassword());
            preparedStatement.setString(3,user.Getemail());
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

}