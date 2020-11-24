package com.dao;

import com.bean.Goods;
import com.utils.JdbcUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;
public class GoodsDao {

    public void addGoods(Goods goods){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //获取String类型的时间
        String id = sdf.format(date);
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into goods values(?,?,?,?,?);";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,goods.Getname());
            preparedStatement.setString(3,goods.Getprice());
            preparedStatement.setInt(4,goods.Getnumber());
            preparedStatement.setString(5,goods.Getprovider());
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
    public List<Goods> showgoods()
    {
        List<Goods> goods_list=new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                String provider=resultSet.getString(5);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }
    //个人商品的展示
    public List<Goods> showgoods(String provider)
    {
        List<Goods> goods_list=new ArrayList<Goods>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where provider=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, provider);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }

    public void update(Goods good) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "update goods set name=?,price=?,number=? where id=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, good.Getname());
            preparedStatement.setString(2, good.Getprice());
            preparedStatement.setInt(3, good.Getnumber());
            preparedStatement.setString(4, good.Getid());
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
    public Goods gotoupdate(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Goods good=new Goods();
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where id=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                String name = rs.getString(2);
                String price = rs.getString(3);
                int number=rs.getInt(4);
                String provider=rs.getString(5);
                good.Setid(id);
                good.Setname(name);
                good.Setprice(price);
                good.Setnumber(number);
                good.Setprovider(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return good;
    }
    public void delete(String id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "delete from goods where id=?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
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

    public Goods Showone(String id)
    {
        Goods good=new Goods();    
        Connection conn=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try{
            conn=JdbcUtils.getConnection();
            String sql="select * from goods where id=?";
            preparedStatement =(PreparedStatement)conn.prepareStatement(sql);
            preparedStatement.setString(1,id);
            rs= preparedStatement.executeQuery();
            if(rs.next())
            {
                String name = rs.getString(2);
                String price = rs.getString(3);
                int number=rs.getInt(4);
                String provider=rs.getString(5);
                good.Setid(id);
                good.Setname(name);
                good.Setnumber(number);
                good.Setprice(price);
                good.Setprovider(provider);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,conn);
        }
        return good;
    }
    public List<Goods> search(String product)
    {
        List<Goods> goods_list=new ArrayList<Goods>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select * from goods where name like ?";
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            product="%"+product+"%";
            preparedStatement.setString(1, product);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String id=resultSet.getString(1);
                String name=resultSet.getString(2);
                String price=resultSet.getString(3);
                int num=resultSet.getInt(4);
                String provider=resultSet.getString(5);
                Goods p=new Goods();
                p.Setid(id);
                p.Setname(name);
                p.Setnumber(num);;
                p.Setprice(price);
                p.Setprovider(provider);
                goods_list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return goods_list;
    }
    
}
