package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IComentario;
import dao.mysql.conexion.ConnectionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comentario;
import model.User;
import utils.JeDate;

/**
 *
 * @author Jerson
 */
public class ComentarioDao implements IComentario{
    
    private ConnectionMysql connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;

    @Override
    public void create(Comentario o) throws CreateException {
        try {
            connection = ConnectionMysql.getInstance();
            conn = connection.connect();
            System.out.println(o.getUser().getIdPerson());
            ct = conn.prepareCall("call sp_insert_comentario(?,?,?)");
            ct.setInt(1, o.getIdPost());
            ct.setInt(2, o.getUser().getIdPerson());
            ct.setString(3, o.getTexto());
            if(ct.executeUpdate()==0){
                throw new CreateException("No se pudo agregar el comentario");
            }
        } catch (SQLException ex) {
            throw new CreateException("No se pudo agregar el comentario");
        } finally{
            cerrarConexiones();
        }
    }

    @Override
    public Comentario read(Integer id) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Comentario o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> all() throws AllException {
        
        List<Comentario> lista = new ArrayList<>(50);

        try {
            connection = ConnectionMysql.getInstance();
            conn =connection.connect();
            pr = conn.prepareStatement("select * from vcomentario order by fecha desc");
            rs = ct.executeQuery();
            if(rs.next()){
                Comentario c = new Comentario();
                User us = new User();
                c.setIdComentario(rs.getInt(1));
                c.setIdPost(rs.getInt(2));
                c.setFecha(JeDate.getTime(new Date(rs.getTimestamp(3).getTime())));
                us.setIdPerson(rs.getInt(4));
                us.setUsername(rs.getString(5));
                us.setUrl(rs.getString(6));
                c.setTexto(rs.getString(7));
                c.setUser(us);
                lista.add(c);
                while(rs.next()){
                    c = new Comentario();
                    us = new User();
                    c.setIdComentario(rs.getInt(1));
                    c.setIdPost(rs.getInt(2));
                    c.setFecha(JeDate.getTime(new Date(rs.getTimestamp(3).getTime())));
                    us.setIdPerson(rs.getInt(4));
                    us.setUsername(rs.getString(5));
                    us.setUrl(rs.getString(6));
                    c.setTexto(rs.getString(7));
                    c.setUser(us);
                    lista.add(c);
                }
            }else{
                throw new AllException("No hay comentarios para este post");
            }
        } catch (SQLException ex) {
            throw new AllException("No hay comentarios para este post");
        }finally{
            cerrarConexiones();
        }
        return lista;
    }

    @Override
    public List<Comentario> all(Integer id) throws AllException {
        List<Comentario> lista = new ArrayList<>();
        try {
            connection = ConnectionMysql.getInstance();
            conn =connection.connect();
            ct = conn.prepareCall("call sp_allUserCommentPost(?)");
            ct.setInt(1, id);
            rs = ct.executeQuery();
            if(rs.next()){
                Comentario c = new Comentario();
                User us = new User();
                c.setIdComentario(rs.getInt(1));
                c.setIdPost(rs.getInt(2));
                c.setFecha(JeDate.getTime(new Date(rs.getTimestamp(3).getTime())));
                us.setIdPerson(rs.getInt(4));
                us.setUsername(rs.getString(5));
                us.setUrl(rs.getString(6));
                c.setTexto(rs.getString(7));
                c.setUser(us);
                lista.add(c);
                while(rs.next()){
                    c = new Comentario();
                    us = new User();
                    c.setIdComentario(rs.getInt(1));
                    c.setIdPost(rs.getInt(2));
                    c.setFecha(JeDate.getTime(new Date(rs.getTimestamp(3).getTime())));
                    us.setIdPerson(rs.getInt(4));
                    us.setUsername(rs.getString(5));
                    us.setUrl(rs.getString(6));
                    c.setTexto(rs.getString(7));
                    c.setUser(us);
                    lista.add(c);
                }
            }else{
                throw new AllException("No hay comentarios para este post");
            }
        } catch (SQLException ex) {
            throw new AllException("No hay comentarios para este post");
        }finally{
            cerrarConexiones();
        }
        return lista;
    }
    
    private void cerrarConexiones() {
        connection.close();
        if(pr!=null){
            try {
                pr.close();
                pr = null;
            } catch (SQLException ex) {}
        }
        if(rs!=null){
            try {
                rs.close();
                rs = null;
            } catch (SQLException ex) {}
        }
        if(ct!=null){
            try {
                ct.close();
                ct = null;
            } catch (SQLException ex) {}
        }
        if(conn!=null){
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    
}
