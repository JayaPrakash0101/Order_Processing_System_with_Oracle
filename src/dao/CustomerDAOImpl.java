/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public class CustomerDAOImpl implements CustomerDAO {

    ConnectionFactory factory;

    public CustomerDAOImpl() {
        this.factory = new MySqlConnectionFactory();
    }
    
    @Override
    public boolean insertCustomer(Customer c) throws Exception {
         Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("insert into customer values(?, ?, ?, ?, ?)");
        ps.setInt(1,c.getCustid());
        ps.setString(2,c.getName());
        ps.setString(3,c.getAddress());
        ps.setString(4,c.getEmail());
        ps.setString(5,c.getMobile());
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;   
    }

    @Override
    public boolean updateCustomer(Customer c) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("update customer set name=?, address=?, email=?, mobile=? where custid=?");
        ps.setInt(5,c.getCustid());
        ps.setString(1,c.getName());
        ps.setString(2,c.getAddress());
        ps.setString(3,c.getEmail());
        ps.setString(4,c.getMobile());
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteCustomer(int custId) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("delete from customer where custid=?");
        ps.setInt(1,custId);
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public Customer getCustomer(int custId) throws Exception {
        Customer c = new Customer();
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select * from customer where custid=?");
        ps.setInt(1,custId);
        //Executing query
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            c.setCustid(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setAddress(rs.getString(3));
            c.setEmail(rs.getString(4));
            c.setMobile(rs.getString(5));
        }
        con.close();
        return c;
    }
    public ArrayList getAllCustomers() throws Exception {
        Customer c;
        Connection con = factory.getConnection();
        ArrayList<Customer> gac = new ArrayList<Customer>();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select * from customer");
        //Executing query
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            c = new Customer();
            c.setCustid(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setAddress(rs.getString(3));
            c.setEmail(rs.getString(4));
            c.setMobile(rs.getString(5));
            gac.add(c);
                
        }
        con.close();
        return gac;
    }
  
}
