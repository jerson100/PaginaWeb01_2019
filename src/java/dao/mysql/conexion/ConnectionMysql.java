package dao.mysql.conexion;

import dao.interfaces.IConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jerson
 */

/*
Aplicamos el patrón de diseño singleton:
para crear una única instancia
 */
public class ConnectionMysql implements IConnection {

    private static final String USER = "root";
    private static final String PASS = "";
    private static final String HOST = "jdbc:mysql://localhost/proyectoWeb01";
    //private static final String DRIVER="com.mysql.jdbc.Driver";
    private static ConnectionMysql connectionMysql;
    private Connection connection;

    private ConnectionMysql() {
        try {
            //Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("No se estableció la conexión a la base de datos mysql " + ex.getMessage());
        }/* catch (ClassNotFoundException ex) {
            System.out.println("Conexión no establecida: "+ex.getMessage());
        }*/
    }

    public static ConnectionMysql getInstance() {
        if (connectionMysql == null) {
            connectionMysql = new ConnectionMysql();
        }
        return connectionMysql;
    }

    @Override
    public Connection connect() {
       return this.connection;
    }

    public void close() {
        try {
            if(!this.connection.isClosed()) {
                this.connection.close();
                this.connection = null;
                this.connectionMysql = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
