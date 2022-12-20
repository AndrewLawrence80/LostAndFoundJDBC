package com.example;

public class UserSignIn
{
    private String userName;
    private String userPassword;

    public UserSignIn(String userName, String userPassword)
    {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserSignIn()
    {
        userName = null;
        userPassword = null;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

}
