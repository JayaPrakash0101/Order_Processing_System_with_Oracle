/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Customer;
import dto.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public class ProductDAOImpl implements ProductDAO {

    ConnectionFactory factory;
    public ProductDAOImpl() {
        this.factory = new MySqlConnectionFactory();
    }

    @Override
    public boolean insertProduct(Product p) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("insert into product values(?, ?, ?, ?, ?)");
        ps.setInt(1,p.getProdid());
        ps.setString(2,p.getName());
        ps.setString(3,p.getDescription());
        ps.setInt(4,p.getQoh());
        ps.setInt(5,p.getRol());
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateProduct(Product p) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("update product set name=?, description=?, qoh=?, rol=? where prodid=?");
        ps.setInt(5,p.getProdid());
        ps.setString(1,p.getName());
        ps.setString(2,p.getDescription());
        ps.setInt(3,p.getQoh());
        ps.setInt(4,p.getRol());
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteProduct(int prodId) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("delete from product where prodid=?");
        ps.setInt(1,prodId);
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public Product getProduct(int prodId) throws Exception {
        Product p = new Product();
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select * from product where prodid=?");
        ps.setInt(1,prodId);
        //Executing query
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            p.setProdid(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setDescription(rs.getString(3));
            p.setQoh(rs.getInt(4));
            p.setRol(rs.getInt(5));
        }
        con.close();
        return p;
    }
    public ArrayList getAllProducts() throws Exception {
        Product p;
        Connection con = factory.getConnection();
        ArrayList<Product> gap = new ArrayList<Product>();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select * from product");
        //Executing query
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            p = new Product();
            p.setProdid(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setDescription(rs.getString(3));
            p.setQoh(rs.getInt(4));
            p.setRol(rs.getInt(5));
            gap.add(p);
                
        }
        con.close();
        return gap;
    }
    public ArrayList showReorderLevelStatus() throws Exception {
        Product p;
        Connection con = factory.getConnection();
        ArrayList<Product> gap = new ArrayList<Product>();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select * from product where qoh < rol");
        //Executing query
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            p = new Product();
            p.setProdid(rs.getInt(1));
            p.setName(rs.getString(2));
            p.setDescription(rs.getString(3));
            p.setQoh(rs.getInt(4));
            p.setRol(rs.getInt(5));
            gap.add(p);
                
        }
        con.close();
        return gap;
    }
}
