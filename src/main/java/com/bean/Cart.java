package com.bean;

public class Cart {
    private String id;
    private String price;
    private String provider;
    private String user;
    private int number;
    private String name;
    public String Getname()
    {
        return this.name;
    }
    public String Getid()
    {
        return this.id;
    }
    public String Getprice()
    {
        return this.price;
    }
    public String Getprovider()
    {
        return this.provider;
    }
    public String Getuser()
    {
        return this.user;
    }
    public int Getnumber()
    {
        return this.number;
    }
    public void Setid(String id)
    {
        this.id=id;
    }
    public void Setprice(String price)
    {
        this.price=price;
    }
    public void Setprovider(String provider)
    {
        this.provider=provider;
    }
    public void Setuser(String user)
    {
        this.user=user;
    }
    public void Setnumber(int number)
    {
        this.number=number;
    }
    public void Setname(String name)
    {
        this.name=name;
    }
}
