package com.example;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionController
{
    private static Connection connection = null;

    private ConnectionController()
    {
    }

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException
    {
        if (connection == null)
        {
            synchronized (ConnectionController.class)
            {
                if (connection == null)
                {
                    File fileConfig = new File("config" + File.separator + "mysql.properties");
                    InputStream inputStream = null;
                    Properties info = null;
                    String user = null;
                    String password = null;
                    String url = null;
                    String driverClass = null;
                    try
                    {
                        inputStream = new FileInputStream(fileConfig);
                        info = new Properties();
                        info.load(inputStream);
                        user = info.getProperty("username");
                        password = info.getProperty("password");
                        url = info.getProperty("url");
                        driverClass = info.getProperty("driverClassName");
                    }
                    finally
                    {
                        if (inputStream != null)
                        {
                            inputStream.close();
                        }
                    }
                    Class.forName(driverClass);
                    connection = DriverManager.getConnection(url, user, password);
                }
            }
        }
        return connection;
    }
}
