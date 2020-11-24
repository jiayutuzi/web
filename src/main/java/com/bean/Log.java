package com.bean;

public class Log
{
    private String time;
    private String id;
    private String name;
    private String price;
    private int number;
    private String user;
    private String provider;
    private String status;
    public Log(String time,String id,String name,String price,int number,String user,String provider,String status)
    {
        this.time=time;
        this.id=id;
        this.name=name;
        this.price=price;
        this.number=number;
        this.user=user;
        this.provider=provider;
        this.status=status;
    }
    public String Gettime()
    {
        return this.time;
    }
    public String Getid()
    {
        return this.id;
    }
    public String Getname()
    {
        return this.name;
    }
    public String Getprice()
    {
        return this.price;
    }
    public int Getnumber()
    {
        return this.number;
    }
    public String Getuser()
    {
        return this.user;
    }
    public String Getprovider()
    {
        return this.provider;
    }
    public String Getstatus()
    {
        return this.status;
    }
}