/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Order;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public interface OrderDAO {
    public boolean insertOrder(Order o) throws Exception;
    public boolean updateOrder(Order o) throws Exception;
    public boolean deleteOrder(int ordId) throws Exception;
    public Order getOrder(int ordId) throws Exception;
    public ArrayList getAllOrders() throws Exception;
}
