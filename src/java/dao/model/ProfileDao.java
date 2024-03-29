package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IProfile;
import dao.mysql.conexion.ConnectionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Profile;

/**
 *
 * @author Jerson
 */
public class ProfileDao implements IProfile {

    private ConnectionMysql connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;

    public ProfileDao() {
    }

    @Override
    public void create(Profile o) throws CreateException {
        connection = ConnectionMysql.getInstance();
        conn = connection.connect();
        try {
            ct = conn.prepareCall("{call sp_insert_profile(?,?,?,?,?,?,?,?,?,?,?)}");
            ct.setInt(1, o.getIdUser());
            ct.setString(2, o.getUrlImage());
            ct.setString(3, o.getName());
            ct.setString(4, o.getLastname());
            ct.setInt(5, o.getAge());
            ct.setInt(6, 1);
            ct.setString(7, o.getDescription());
            ct.setString(8, o.getTwitter());
            ct.setString(9, o.getFacebook());
            ct.setString(10, o.getYoutube());
            ct.setString(11, o.getInstagram());
            if (ct.executeUpdate() == 0) {
                throw new CreateException("No se pudo crear el perfil");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CreateException("No se pudo crear el perfil");
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public Profile read(Integer id) throws ReadException {
        connection = ConnectionMysql.getInstance();
        conn = connection.connect();
        Profile p = null;
        try {
            pr = conn.prepareStatement("select * from vprofile where idUser = ?");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                p = new Profile(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11));
            } else {
                throw new ReadException("No se encontró el perfil");
            }
        } catch (SQLException e) {
            throw new ReadException("No se encontró el perfil");
        } finally {
            cerrarConexiones();
        }
        return p;
    }

    @Override
    public boolean update(Profile o) throws UpdateException {
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            ct = conn.prepareCall("call sp_update_profile(?,?,?,?,?,?,?,?,?,?,?)");
            ct.setInt(1, o.getIdUser());
            ct.setString(2, o.getUrlImage());
            ct.setString(3, o.getName());
            ct.setString(4, o.getLastname());
            ct.setInt(5, o.getAge());
            ct.setInt(6, o.getIdCountry());
            ct.setString(7, o.getDescription());
            ct.setString(8, o.getTwitter());
            ct.setString(9, o.getFacebook());
            ct.setString(10, o.getInstagram());
            ct.setString(11, o.getYoutube());
            if (ct.executeUpdate() == 0) {
                throw new UpdateException("No se pudo actualizar el perfil");
            }
        } catch (SQLException ex) {
            throw new UpdateException("No se pudo actualizar el perfil");
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
    public List<Profile> all() throws AllException {
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
    public List<Profile> all(Integer id) throws AllException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getImageProfile(int id) throws ReadException {
        String urlImage = null;
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select urlImage from vprofile where idUser=?");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if (rs.next()) {
                urlImage = rs.getString(1);
            } else {
                throw new ReadException("No se encontró la url de la imagen para ese perfil");
            }
        } catch (SQLException ex) {
            throw new ReadException("No se encontró la url de la imagen para ese perfil");
        } finally {
            cerrarConexiones();
        }

        return urlImage;
    }

}
