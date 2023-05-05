/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dto.Dispatch;
import java.util.ArrayList;

/**
 *
 * @author P. Jaya Prakash
 */
public interface DispatchDAO {
    public boolean dispatchOrder(Dispatch d) throws Exception;
    public int generateDispatchID() throws Exception;
    public ArrayList populateOrderIDs() throws Exception;
    public ArrayList populateProductIDs(int ordId) throws Exception;
    public ArrayList getAllDispatches() throws Exception;
    public int getDispatchQty(int ordId, int prodId) throws Exception;
    public int getQoh(int prodId) throws Exception;
}
