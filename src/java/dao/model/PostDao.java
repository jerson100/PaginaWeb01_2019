package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IPost;
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
import model.Post;
import model.User;
import utils.JeDate;

/**
 *
 * @author Jerson
 */
public class PostDao implements IPost {

    private CallableStatement cbl;
    private ConnectionMysql connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;

    @Override
    public void create(Post o) throws CreateException {
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            cbl = conn.prepareCall("call sp_insert_post(?,?,?)");
            cbl.setInt(1, o.getUser().getIdPerson());
            cbl.setString(2, o.getTitle());
            cbl.setString(3, o.getUrlImage());
            if (cbl.executeUpdate() == 0) {
                throw new CreateException("No se pudo crear el post");
            }
        } catch (SQLException ex) {
            throw new CreateException("No se pudo crear el post");
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public Post read(Integer id) throws ReadException {
        Post post = null;
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select * from vpost where idPost = ?");
            pr.setInt(1,id);
            rs = pr.executeQuery();
            if (rs.next()) {
                post = new Post();
                User us = new User();
                us.setIdPerson(rs.getInt(2));
                post.setIdPost(rs.getInt(1));
                post.setTitle(rs.getString(3));
                post.setUrlImage(rs.getString(4));
                post.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                post.setCountLikes(rs.getInt(6));
                us.setUsername(rs.getString(7));
                us.setIdTypeUser(rs.getInt(8));
                us.setUrl(rs.getString(9));
                post.setUser(us);
            } else {
                throw new ReadException("No se encontraró el post");
            }
        } catch (SQLException ex) {
            throw new ReadException("No se encontraró el post");
        } finally {
            cerrarConexiones();
        }
        return post;
    }

    @Override
    public boolean update(Post o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> all(Integer id) throws AllException {
        List<Post> post = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select * from vpost where idUser=?");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            User us = new User();
            if (rs.next()) {
                Post l = new Post();
                us.setIdPerson(rs.getInt(2));
                l.setIdPost(rs.getInt(1));
                l.setUser(us);
                l.setTitle(rs.getString(3));
                l.setUrlImage(rs.getString(4));
                l.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                l.setCountLikes(rs.getInt(6));
                post.add(l);
                while (rs.next()) {
                    l = new Post();
                    us = new User();
                    us.setIdPerson(rs.getInt(2));
                    l.setIdPost(rs.getInt(1));
                    l.setUser(us);
                    l.setTitle(rs.getString(3));
                    l.setUrlImage(rs.getString(4));
                    l.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                    l.setCountLikes(rs.getInt(6));
                    post.add(l);
                }
            } else {
                throw new AllException("Aún no realiza ninguna publicación");
            }
        } catch (SQLException ex) {
            throw new AllException("Aún no realiza ninguna publicación");
        } finally {
            cerrarConexiones();
        }
        return post;
    }

    @Override
    public List<Post> all() throws AllException {
        List<Post> post = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select * from vpost");
            rs = pr.executeQuery();
            if (rs.next()) {
                Post l = new Post();
                User us = new User();
                us.setIdPerson(rs.getInt(2));
                l.setIdPost(rs.getInt(1));
                l.setTitle(rs.getString(3));
                l.setUrlImage(rs.getString(4));
                l.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                l.setCountLikes(rs.getInt(6));
                us.setUsername(rs.getString(7));
                us.setIdTypeUser(rs.getInt(8));
                us.setUrl(rs.getString(9));
                l.setUser(us);
                post.add(l);
                while (rs.next()) {
                    l = new Post();
                    us = new User();
                    us.setIdPerson(rs.getInt(2));
                    l.setIdPost(rs.getInt(1));
                    l.setTitle(rs.getString(3));
                    l.setUrlImage(rs.getString(4));
                    l.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                    l.setCountLikes(rs.getInt(6));
                    us.setUsername(rs.getString(7));
                    us.setIdTypeUser(rs.getInt(8));
                    us.setUrl(rs.getString(9));
                    l.setUser(us);
                    post.add(l);
                }
            } else {
                throw new AllException("No se encontrarón Post");
            }
        } catch (SQLException ex) {
            throw new AllException("No se encontrarón Post");
        } finally {
            cerrarConexiones();
        }
        return post;
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
    public List<Post> all(int i,int j) throws AllException {
        List<Post> post = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select * from vpost limit ?,?");
            pr.setInt(1, i);
            pr.setInt(2, j);
            rs = pr.executeQuery();
            Date fecha_p;
            if (rs.next()) {
                Post l = new Post();
                User us = new User();
                us.setIdPerson(rs.getInt(2));
                l.setIdPost(rs.getInt(1));
                l.setTitle(rs.getString(3));
                l.setUrlImage(rs.getString(4));
                fecha_p = new Date(rs.getTimestamp(5).getTime());
                l.setDatePost(fecha_p);
                l.setDateFormat(JeDate.getTime(fecha_p));
                l.setCountLikes(rs.getInt(6));
                us.setUsername(rs.getString(7));
                us.setIdTypeUser(rs.getInt(8));
                us.setUrl(rs.getString(9));
                l.setUser(us);
                post.add(l);
                while (rs.next()) {
                    l = new Post();
                    us = new User();
                    us.setIdPerson(rs.getInt(2));
                    l.setIdPost(rs.getInt(1));
                    l.setTitle(rs.getString(3));
                    l.setUrlImage(rs.getString(4));
                    fecha_p = new Date(rs.getTimestamp(5).getTime());
                    l.setDatePost(fecha_p);
                    l.setDateFormat(JeDate.getTime(fecha_p));
                    l.setCountLikes(rs.getInt(6));
                    us.setUsername(rs.getString(7));
                    us.setIdTypeUser(rs.getInt(8));
                    us.setUrl(rs.getString(9));
                    l.setUser(us);
                    post.add(l);
                }
            } else {
                throw new AllException("No se encontrarón Post");
            }
        } catch (SQLException ex) {
            throw new AllException("No se encontrarón Post");
        } finally {
            cerrarConexiones();
        }
        return post;
    }

    @Override
    public int countRegister() {
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select count(*) from vpost");
            rs = pr.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            cerrarConexiones();
        }
        return 0;
    }

    @Override
    public List<Post> all(int id, int i, int j) throws AllException {
        List<Post> post = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            pr = conn.prepareStatement("select * from vpost where idUser=? limit ?,?");
            pr.setInt(1, id);
            pr.setInt(2, i);
            pr.setInt(3, j);
            rs = pr.executeQuery();
            if (rs.next()) {
                Post l = new Post();
                User us = new User();
                us.setIdPerson(rs.getInt(2));
                l.setIdPost(rs.getInt(1));
                l.setTitle(rs.getString(3));
                l.setUrlImage(rs.getString(4));
                l.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                l.setCountLikes(rs.getInt(6));
                us.setUsername(rs.getString(7));
                us.setIdTypeUser(rs.getInt(8));
                us.setUrl(rs.getString(9));
                l.setUser(us);
                post.add(l);
                while (rs.next()) {
                    l = new Post();
                    us = new User();
                    us.setIdPerson(rs.getInt(2));
                    l.setIdPost(rs.getInt(1));
                    l.setTitle(rs.getString(3));
                    l.setUrlImage(rs.getString(4));
                    l.setDatePost(new Date(rs.getTimestamp(5).getTime()));
                    l.setCountLikes(rs.getInt(6));
                    us.setUsername(rs.getString(7));
                    us.setIdTypeUser(rs.getInt(8));
                    us.setUrl(rs.getString(9));
                    l.setUser(us);
                    post.add(l);
                }
            } else {
                throw new AllException("No se encontrarón Post");
            }
        } catch (SQLException ex) {
            throw new AllException("No se encontrarón Post");
        } finally {
            cerrarConexiones();
        }
        return post;
    }

}
