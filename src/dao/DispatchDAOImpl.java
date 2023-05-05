/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Dispatch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public class DispatchDAOImpl implements DispatchDAO {

    ConnectionFactory factory;
    
     public boolean dispatchOrder(Dispatch d) throws Exception {
        Connection con = factory.getConnection();
        
        PreparedStatement ps = con.prepareStatement("insert into dispatch values(?, ?, ?, ?, ?)");
        ps.setInt(1,d.getDispatchid());
        ps.setInt(2,d.getOrdid());
        ps.setInt(3,d.getProdid());
        ps.setString(4,d.getDispatchDate());
        ps.setInt(5,d.getQty());
        //Executing query
        int x = ps.executeUpdate();
        PreparedStatement ps1 = con.prepareStatement("update product set qoh=qoh-? where prodid=?");
        ps1.setInt(1,d.getQty());
        ps1.setInt(2,d.getProdid());
        int x1 = ps1.executeUpdate();
        con.close();
        if(x==1 && x1==1)
            return true;
        else
            return false;
    } 
    
    public DispatchDAOImpl() {
        this.factory = new MySqlConnectionFactory();
    }

    @Override
    public int generateDispatchID() throws Exception {
        Connection con = factory.getConnection();
        
        PreparedStatement ps = con.prepareStatement("select max(dispatchid) from dispatch");
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            int max=rs.getInt(1);
            max++;
            return max;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public ArrayList populateOrderIDs() throws Exception {
        Connection con = factory.getConnection();
        
        PreparedStatement ps = con.prepareStatement("select ordid from orders");
        ResultSet rs = ps.executeQuery();
        ArrayList list = new ArrayList();
        while(rs.next())
        {
            list.add(rs.getInt(1));
        }
        return list;
    }

    @Override
    public ArrayList populateProductIDs(int ordId) throws Exception {
        Connection con = factory.getConnection();
        
        PreparedStatement ps = con.prepareStatement("select od.prodid, o.ordid from orders o inner join orderdetails od on o.ordid=od.ordid where od.ordid=?");
        ps.setInt(1, ordId);
        ResultSet rs = ps.executeQuery();
        ArrayList list = new ArrayList();
        while(rs.next())
        {
            list.add(rs.getInt(1));
        }
        return list;
    }
    public ArrayList getAllDispatches() throws Exception {
        Dispatch d;
        Connection con = factory.getConnection();
        ArrayList<Dispatch> gad = new ArrayList<Dispatch>();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select * from dispatch");
        //Executing query
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            d = new Dispatch();
            d.setDispatchid(rs.getInt(1));
            d.setOrdid(rs.getInt(2));
            d.setProdid(rs.getInt(3));
            d.setDispatchDate(rs.getString(4));
            d.setQty(rs.getInt(5));
            gad.add(d);
                
        }
        con.close();
        return gad;
    }
    public int getDispatchQty(int ordId, int prodId) throws Exception {
        Connection con = factory.getConnection();
        int qty = 0;
        PreparedStatement ps = con.prepareStatement("select qty from orderdetails where ordid=? and prodid=?");
        //Executing query
        ps.setInt(1, ordId);
        ps.setInt(2, prodId);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            qty = rs.getInt(1);
        }
        con.close();
        return qty;
    }
    public int getQoh(int prodId) throws Exception {
        Connection con = factory.getConnection();
        int qoh = 0;
        PreparedStatement ps = con.prepareStatement("select qoh from product where prodId=?");
        //Executing query
        ps.setInt(1, prodId);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            qoh = rs.getInt(1);
        }
        con.close();
        return qoh;
    }
}
