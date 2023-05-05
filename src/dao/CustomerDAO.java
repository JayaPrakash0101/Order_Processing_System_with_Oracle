/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Customer;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public interface CustomerDAO {
    public boolean insertCustomer(Customer c) throws Exception;
    public boolean updateCustomer(Customer c) throws Exception;
    public boolean deleteCustomer(int custId) throws Exception;
    public Customer getCustomer(int custId) throws Exception;
    public ArrayList getAllCustomers() throws Exception;
}
