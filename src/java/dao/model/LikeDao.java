package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.ILike;
import dao.mysql.conexion.ConnectionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Like;

/**
 *
 * @author Jerson
 */
public class LikeDao implements ILike {

    private CallableStatement cbl;
    private ConnectionMysql connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;

    @Override
    public void create(Like o) throws CreateException {
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            cbl = conn.prepareCall("call sp_insert_postUser(?,?)");
            cbl.setInt(1, o.getIdPost());
            cbl.setInt(2, o.getIdUser());
            if (cbl.executeUpdate() == 0) {
                throw new CreateException("No se pudo dar like a la publicación");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CreateException("No se pudo dar like a la publicación");
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public Like read(Integer id) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Like o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Like> all() throws AllException {
        List<Like> likes = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select * from vpostuser");
            rs = pr.executeQuery();
            if (rs.next()) {
                Like l = new Like();
                l.setIdPost(rs.getInt(1));
                l.setIdUser(rs.getInt(2));
                l.setDatePostU(new Date(rs.getDate(3).getTime()));
                likes.add(l);
                while (rs.next()) {
                    l = new Like();
                    l.setIdPost(rs.getInt(1));
                    l.setIdUser(rs.getInt(2));
                    l.setDatePostU(new Date(rs.getDate(3).getTime()));
                    likes.add(l);
                }
            } else {
                throw new AllException("No se encontrarón likes");
            }
        } catch (SQLException ex) {
            throw new AllException("No se encontrarón likes");
        } finally {
            cerrarConexiones();
        }
        return likes;
    }

    private void cerrarConexiones() {
        connection.close();
        connection = null;
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
        if (cbl != null) {
            try {
                cbl.close();
                cbl = null;
            } catch (SQLException ex) {
                Logger.getLogger(LikeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Like> all(Integer id) throws AllException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> getPostUserFromUserLike(int idUserLike, int idUserPost) throws AllException {
        List<Integer> list = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            cbl = conn.prepareCall("call sp_all_postLikeUser(?,?)");
            cbl.setInt(1, idUserLike);
            cbl.setInt(2, idUserPost);
            rs = cbl.executeQuery();
            if (rs.next()) {
                list.add(rs.getInt(1));
                while (rs.next()) {
                   list.add(rs.getInt(1));
                }
            } else {
                throw new AllException("El usuario: " + idUserLike 
                        + "no a dado like a una publicación del usuario con id: " + idUserPost);
            }
        } catch (SQLException ex) {
            throw new AllException("El usuario: " + idUserLike +
                    "no a dado like a una publicación del usuario con id: " + idUserPost);
        } finally {
            cerrarConexiones();
        }
        return list;
    }

}
