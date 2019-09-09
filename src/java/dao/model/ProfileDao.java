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
import dao.interfaces.IProfile;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Profile;

/**
 *
 * @author Jerson
 */
public class ProfileDao implements IProfile{
    
    private IConnection connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;
    
    public ProfileDao(IConnection conn){
        this.connection = conn;
    }

    @Override
    public void create(Profile o) throws CreateException {
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_insert_profile(?,?,?,?,?,?,?,?,?,?,?,?)}");
            ct.setString(1, o.getUrlImage());
            ct.setInt(2, o.getState());
            ct.setString(3,o.getName());
            ct.setString(4, o.getLastname());
            ct.setInt(5, o.getAge());
            ct.setInt(6, o.getIdCountry());
            ct.setString(7, o.getDescription());
            ct.setString(8,o.getTwitter());
            ct.setString(9,o.getFacebook());
            ct.setString(10,o.getYoutube());
            ct.setString(11,o.getInstagram());
            ct.setString(12,o.getUrlProfile());
            if(ct.executeUpdate()==0){
                throw new CreateException("No se pudo crear el perfil");
            }else{
                System.err.println("Se creo el id del profile");
              rs= ct.getGeneratedKeys();
              if (rs.next()) {
                o.setIdUser(rs.getInt(1));
              }
            }
        } catch (SQLException e) {
           throw new CreateException("No se pudo crear el perfil");
        } finally{
          cerrarConexiones();
        }
    }

    @Override
    public Profile read(Integer id) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Profile o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profile> all() throws AllException {
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
