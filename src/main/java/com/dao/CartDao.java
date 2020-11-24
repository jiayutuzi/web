package com.dao;

import com.bean.Cart;
import com.utils.JdbcUtils;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;  
import java.sql.*;


public class CartDao {
    public List<Cart> showCart(String user)
    {
        List<Cart> cart=new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from cart where user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,user);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                String provider=resultSet.getString(4);
                int num=resultSet.getInt(5);
                Cart p=new Cart();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                p.Setuser(user);
                cart.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return cart;
    }
    public void addcart(Cart cart){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into cart values(?,?,?,?,?,?)";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,cart.Getid());
            preparedStatement.setString(2,cart.Getname());
            preparedStatement.setString(3,cart.Getprice());
            preparedStatement.setString(4,cart.Getprovider());
            preparedStatement.setInt(5,cart.Getnumber());
            preparedStatement.setString(6,cart.Getuser());
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
    public Cart gotoupdate(String id,String user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Cart cart=new Cart();
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from cart where id=? and user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, user);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                String name = rs.getString(2);
                String price = rs.getString(3);
                int number=rs.getInt(5);
                String provider=rs.getString(4);
                cart.Setid(id);
                cart.Setname(name);
                cart.Setprice(price);
                cart.Setnumber(number);
                cart.Setprovider(provider);
                cart.Setuser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return cart;
    }

    public void update(Cart cart) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update cart set number=? where id=? and user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setInt(1, cart.Getnumber());
            preparedStatement.setString(2, cart.Getid());
            preparedStatement.setString(3, cart.Getuser());
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
    public void delete(String id,String user)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from cart where id=? and user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, user);
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
    public String Getprice(String user)
    {
        double sum=0;
        String sum_price=null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select price,number from cart where user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                String p = rs.getString(1);
                int number=rs.getInt(2);
                double price=Double.parseDouble(p);
                sum+=price*number;
            }
            DecimalFormat df= new DecimalFormat("######0.00"); 
            sum_price=df.format(sum);

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return sum_price;
    }
    public void deleteall(String user)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from cart where user=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
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
