/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;

/**
 *
 * @author P. Jaya Prakash
 */
public interface ConnectionFactory {
    public Connection getConnection() throws Exception;
    //public Connection getConnectionUsingPropertiesFile() throws Exception;
    
}
