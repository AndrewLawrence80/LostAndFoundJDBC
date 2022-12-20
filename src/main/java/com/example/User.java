package com.example;

public class User
{
    private String userName;
    private String name;
    private String address;
    private String phone;
    private String WeChat;
    private String QQ;
    private String Email;

    public User()
    {
    }

    public User(String userName, String name, String address, String phone, String weChat, String QQ, String email)
    {
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.phone = phone;
        WeChat = weChat;
        this.QQ = QQ;
        Email = email;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getWeChat()
    {
        return WeChat;
    }

    public void setWeChat(String weChat)
    {
        WeChat = weChat;
    }

    public String getQQ()
    {
        return QQ;
    }

    public void setQQ(String QQ)
    {
        this.QQ = QQ;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }
}
