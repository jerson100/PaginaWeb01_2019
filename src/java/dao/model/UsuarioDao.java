package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IConnection;
import dao.interfaces.IUsuario;
import dao.oracle.conexion.ConnectionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * @param o el usuario a crear y agregar a la bd
     * @throws CreateException Si no se crea el objeto
     */
    
    @Override
    public void create(User o) throws CreateException {
        connection = ConnectionOracle.getInstance();
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_insert_users(?,?,?,?)}");
            if(ct.executeUpdate()==0){
                throw new CreateException("No se pudo crear al usuario");
            }
        } catch (SQLException ex) {
            throw new CreateException("No se pudo crear el usuario");
        } finally{
            cerrarConexiones();
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
