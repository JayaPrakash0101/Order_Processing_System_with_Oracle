/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Customer;
import dto.Order;
import dto.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public class OrderDAOImpl implements OrderDAO {

    ConnectionFactory factory;
    public OrderDAOImpl() {
        this.factory = new MySqlConnectionFactory();
    }

    @Override
    public boolean insertOrder(Order o) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("insert into orders values(?, ?, ?, ?)");
        ps.setInt(1,o.getOrdid());
        ps.setInt(2,o.getCustid());
        ps.setString(3,o.getOrderdate());
        ps.setString(4,o.getOrderstatus());
        //Executing query
        int x = ps.executeUpdate();
        PreparedStatement ps1 = con.prepareStatement("insert into orderdetails values(?, ?, ?)");
        ps1.setInt(1,o.getOrdid());
        ps1.setInt(2,o.getProdid());
        ps1.setInt(3,o.getQty());
        int x1 = ps1.executeUpdate();
        con.close();
        if(x==1 && x1==1)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateOrder(Order o) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("update orders set ordid=?, custid=?, orderdate=?, orderstatus=? where prodid=?");
        ps.setInt(1,o.getOrdid());
        ps.setInt(2,o.getCustid());
        ps.setString(3,o.getOrderdate());
        ps.setString(4,o.getOrderstatus());
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteOrder(int ordId) throws Exception {
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("delete from orders where ordid=?");
        ps.setInt(1,ordId);
        //Executing query
        int x = ps.executeUpdate();
        con.close();
        if(x==1)
            return true;
        else
            return false;
    }

    @Override
    public Order getOrder(int ordId) throws Exception {
        Order o = new Order();
        Connection con = factory.getConnection();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select o1.ordid,o1.custid,o1.orderdate,o1.orderstatus,o2.prodid,o2.qty from orders o1 inner join orderdetails o2 on o1.ordid = o2.ordid where o1.ordid=?;");
        ps.setInt(1,ordId);
        //Executing query
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            o.setOrdid(rs.getInt(1));
            o.setCustid(rs.getInt(2));
            o.setOrderdate(rs.getString(3));
            o.setOrderstatus(rs.getString(4));
            o.setProdid(rs.getInt(5));
            o.setQty(rs.getInt(6));
        }
        con.close();
        return o;
    }
    public ArrayList getAllOrders() throws Exception {
        Order o;
        Connection con = factory.getConnection();
        ArrayList<Order> gao = new ArrayList<Order>();
        //Creating statement object
        PreparedStatement ps = con.prepareStatement("select o.ordid, o.custid, o.orderdate, od.prodid, od.qty, o.orderstatus from orders as o inner join orderdetails as od on o.ordid = od.ordid;");
        //Executing query
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            o = new Order();
            o.setOrdid(rs.getInt(1));
            o.setCustid(rs.getInt(2));
            o.setOrderdate(rs.getString(3));
            o.setProdid(rs.getInt(4));
            o.setQty(rs.getInt(5));
            o.setOrderstatus(rs.getString(6));
            gao.add(o);
                
        }
        con.close();
        return gao;
    }
}
