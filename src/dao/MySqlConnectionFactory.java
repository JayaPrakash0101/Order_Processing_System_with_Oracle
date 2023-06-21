/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
/**
 *
 * @author P. Jaya Prakash
 */
public class MySqlConnectionFactory implements ConnectionFactory {
    
    /*public Connection getConnection() throws Exception
    {
        //Loading JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Establishing connection to database
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ops","root","JayaJaya1016$");
        //System.out.println(url);
        return con;
    }*/
    public Connection getConnection() throws Exception
    {
        FileReader reader=new FileReader("D:\\Projects\\Project-4 (OPS with Oracle)\\OPS_Oracle\\src\\dao\\Oracle.properties");
        Properties p = new Properties();
        p.load(reader);
        //Loading JDBC driver
        Class.forName(p.getProperty("DriverClass"));
        //Establishing connection to database
        //String url = p.getProperty("ConnectionString") +","+ p.getProperty("UserID") +","+ p.getProperty("Password");
        String url = p.getProperty("ConnectionString");
        String userId= p.getProperty("UserID");
        String password=p.getProperty("Password");
        System.out.println(url);
        Connection con=DriverManager.getConnection(url,userId,password);
        System.out.println(url);
        return con;
    }
}
