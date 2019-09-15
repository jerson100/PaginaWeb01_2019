/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IConnection;
import dao.interfaces.ITypeUser;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.TypeUser;

/**
 *
 * @author Jerson
 */
public class TypeUserDao implements ITypeUser{

    private IConnection connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;
    
    public TypeUserDao(IConnection conn){
        this.connection = conn;
    }
    
    @Override
    public void create(TypeUser o) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TypeUser read(Integer id) throws ReadException {
        TypeUser t=null;
        conn = connection.connect();
        try {
            pr = conn.prepareStatement("select * from vTypeUser where idTypeUser=?");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if(rs.next()){
                t = new TypeUser(rs.getInt(1),rs.getString(2),rs.getString(3));
            }else{
               throw new ReadException("No se encontró el tipo de usuario");
            }
        } catch (SQLException e) {
        throw new ReadException("No se encontró el tipo de usuario");
        } finally{
            cerrarConexiones();
        }
        return t;
    }

    @Override
    public boolean update(TypeUser o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypeUser> all() throws AllException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void cerrarConexiones() {
        if(pr!=null){
            try {
                pr.close();
            } catch (SQLException ex) {}
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException ex) {}
        }
        if(ct!=null){
            try {
                ct.close();
            } catch (SQLException ex) {}
        }
        if(conn!=null){
            conn = null;
        }
    }
    
}
