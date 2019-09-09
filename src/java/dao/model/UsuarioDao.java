package dao.model;

import dao.exceptions.AccesDeneg;
import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IConnection;
import dao.interfaces.IUsuario;
import dao.mysql.conexion.ConnectionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Session;
import model.User;

/**
 *
 * @author Jerson
 */
public class UsuarioDao implements IUsuario{

    private IConnection connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;
    
    public UsuarioDao(IConnection conn){
        this.connection = conn;
    }
    
    /**
     * Nos permite crear Objetos de tipo usuario y almacenar en la bd
     * @param username
     * @param password
     * @return 
     * @throws dao.exceptions.AccesDeneg
     */
    
    @Override
    public User login(String username, String password) throws AccesDeneg {
        User us = null;
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_login(?,?)}");
            ct.setString(1, username);
            ct.setString(2, password);  
            rs = ct.executeQuery();
            if(rs.next()) {
                us = new User(username, password, rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4));
            }else{
                throw new AccesDeneg("Acceso denegado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AccesDeneg("Acceso denegado");
        } finally {
            cerrarConexiones();
        }
        return us;
    }
    
    @Override
    public void create(User o) throws CreateException {
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_insert_users(?,?,?,?)}");
            ct.setString(1, o.getUsername());
            ct.setString(2, o.getPass());
            ct.setString(3, o.getEmail());
            ct.setInt(4, o.getIdTypeUser());
            rs = ct.executeQuery();
            if(rs.next()){
                o.setIdPerson(rs.getInt(1));
            }else{
               throw new CreateException("No se pudo crear al usuario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CreateException("No se pudo crear el usuario");
        } finally{
            cerrarConexiones();
        }
        
    }

    @Override
    public User read(Integer id) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> all() throws AllException {
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
