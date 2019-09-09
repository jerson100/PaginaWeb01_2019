/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle.conexion;

import dao.interfaces.IConnection;
import java.sql.Connection;

/**
 *
 * @author Jerson
 */
public class ConnectionOracle implements IConnection{

    @Override
    public Connection connect() {
       return null;
    }
    
    public static ConnectionOracle getInstance(){
        return null;
    }
    
}
