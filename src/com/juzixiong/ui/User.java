package com.juzixiong.ui;

public class User
{
    //用户名
    String username;
    //密码
    String password;
    //构造方法
    public User() {
    }
    //有参构造
    public User(String username , String password)
    {
        this.username = username;
        this.password = password;
    }


    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + "}";
    }
}
