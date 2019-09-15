package dao.model;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.IConnection;
import dao.interfaces.ICountry;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Country;

/**
 *
 * @author Jerson
 */
public class CountryDao implements ICountry {
    private IConnection connection;
    private Connection conn;
    private PreparedStatement pr;
    private CallableStatement ct;
    private ResultSet rs;
    
    public CountryDao(IConnection conn){
        this.connection = conn;
    }
    @Override
    public void create(Country o) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Country read(Integer id) throws ReadException {
        Country c = null;
        conn = connection.connect();
        try {
            pr = conn.prepareStatement("select * from vcountry where idCountry = ?");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            if(rs.next()){
                c = new Country(rs.getInt(1), rs.getString(2));
            }else{
                throw new ReadException("No se encontró el país");
            }
        } catch (SQLException e) {
           throw new ReadException("No se encontró el país");
        } finally{
            cerrarConexiones();
        }
        return c;
    }

    @Override
    public boolean update(Country o) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Country> all() throws AllException {
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
