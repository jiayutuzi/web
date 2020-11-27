package com.bean;

public class User {
    private String name;
    private String password;
    private String email;
    public String Getname(){  //获取用户姓名
        return this.name;
    }
    public String Getpassword(){
        return this.password;
    }
    public String Getemail(){
        return this.email;
    }
    public void Setname(String name){ //设置用户姓名
        this.name=name;
    }
    public void Setpassword(String password){
        this.password=password;
    }
    public void Setemail(String email){
        this.email=email;
    }
}

