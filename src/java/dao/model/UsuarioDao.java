package dao.model;

import dao.exceptions.AccesDeneg;
import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IUsuario;
import dao.mysql.conexion.ConnectionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Jerson
 */
public class UsuarioDao implements IUsuario {

    private ConnectionMysql connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;

    public UsuarioDao() {
    }

    /**
     * Nos permite crear Objetos de tipo usuario y almacenar en la bd
     *
     * @param username
     * @param password
     * @return
     * @throws dao.exceptions.AccesDeneg
     */
    @Override
    public User login(String username, String password) throws AccesDeneg {
        User us = null;
        connection = ConnectionMysql.getInstance();
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_login(?,?)}");
            ct.setString(1, username);
            ct.setString(2, password);
            rs = ct.executeQuery();
            if (rs.next()) {
                us = new User(rs.getInt(1), username, password, rs.getInt(2), rs.getInt(3), rs.getString(4));
            } else {
                throw new AccesDeneg("Acceso denegado");
            }
        } catch (SQLException e) {
            throw new AccesDeneg("Acceso denegado");
        } finally {
            cerrarConexiones();
        }
        return us;
    }

    @Override
    public void create(User o) throws CreateException {
        connection = ConnectionMysql.getInstance();
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_insert_users(?,?,?,?)}");
            ct.setString(1, o.getUsername());
            ct.setString(2, o.getPass());
            ct.setString(3, o.getEmail());
            ct.setInt(4, o.getIdTypeUser());
            rs = ct.executeQuery();
            if (rs.next()) {
                o.setIdPerson(rs.getInt(1));
            } else {
                throw new CreateException("No se pudo crear al usuario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CreateException("No se pudo crear el usuario");
        } finally {
            cerrarConexiones();
        }

    }

    @Override
    public User read(Integer id) throws ReadException {
        connection = ConnectionMysql.getInstance();
        User us = null;
        conn = connection.connect();
        try {
            pr = conn.prepareStatement("select * from vusers where idUser=?");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            } else {
                throw new ReadException("No se encontró el usuario");
            }
        } catch (SQLException e) {
            throw new ReadException("No se encontró el usuario");
        } finally {
            cerrarConexiones();
        }
        return us;
    }

    @Override
    public boolean update(User o) throws UpdateException {
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            ct = conn.prepareCall("call sp_update_users(?,?,?,?,?)");
            ct.setInt(1, o.getIdPerson());
            ct.setString(2, o.getUsername());
            ct.setString(3, o.getPass());
            ct.setString(4, o.getEmail());
            ct.setInt(5, o.getIdTypeUser());
            if (ct.executeUpdate() == 0) {
                throw new UpdateException("No se pudo actualizar el usuario");
            }
        } catch (SQLException ex) {
            throw new UpdateException("No se pudo actualizar el usuario");
        } finally {
            cerrarConexiones();
        }
        return true;
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
        connection.close();
        if (pr != null) {
            try {
                pr.close();
                pr = null;
            } catch (SQLException ex) {
            }
        }
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {
            }
        }
        if (ct != null) {
            try {
                ct.close();
                ct = null;
            } catch (SQLException ex) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public List<User> all(Integer id) throws AllException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public List<User> allUserLikePost(int idPost) throws AllException {
        List<User> users = new ArrayList<>();
        connection = ConnectionMysql.getInstance();
        try {
            conn = connection.connect();
            ct = conn.prepareCall("call sp_allUserLikePost(?)");
            ct.setInt(1, idPost);
            rs = ct.executeQuery();
            if (rs.next()) {
                User us = new User();
                us.setIdPerson(rs.getInt(1));
                us.setUsername(rs.getString(2));
                us.setIdTypeUser(rs.getInt(3));
                us.setUrl(rs.getString(4));
                users.add(us);
                while (rs.next()) {
                    us = new User();
                    us.setIdPerson(rs.getInt(1));
                    us.setUsername(rs.getString(2));
                    us.setIdTypeUser(rs.getInt(3));
                    us.setUrl(rs.getString(4));
                    users.add(us);
                }
            } else {
                throw new AllException("Aún no han dado like a esa publicación");
            }
        } catch (SQLException ex) {
            throw new AllException("Aún no han dado like a esa publicación");
        } finally {
            cerrarConexiones();
        }
        return users;
    }

    @Override
    public List<User> lastRegisteredUsers(int count) throws AllException {
        connection = ConnectionMysql.getInstance();
        User us = null;
        conn = connection.connect();
        List<User> users = new ArrayList<>();
        try {
            pr = conn.prepareStatement("select * from vusers order by idUser desc limit ?");
            pr.setInt(1, count);
            rs = pr.executeQuery();
            if (rs.next()) {
                us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                us.setUrl(rs.getString(6));
                users.add(us);
                while(rs.next()){
                    us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    us.setUrl(rs.getString(6));
                    users.add(us);
                }
            } else {
                throw new AllException("No se encontraron usuarios");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AllException("No se encontraron usuarios");
        } finally {
            cerrarConexiones();
        }
        return users;
    }

}
