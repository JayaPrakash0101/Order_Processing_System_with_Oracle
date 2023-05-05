/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Product;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public interface ProductDAO {
    public boolean insertProduct(Product c) throws Exception;
    public boolean updateProduct(Product c) throws Exception;
    public boolean deleteProduct(int prodId) throws Exception;
    public Product getProduct(int prodId) throws Exception;
    public ArrayList getAllProducts() throws Exception;
    public ArrayList showReorderLevelStatus() throws Exception;
}
