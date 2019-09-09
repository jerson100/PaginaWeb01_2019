package dao.manager;

import dao.enums.EDaoManager;
import dao.interfaces.IConnection;
import dao.interfaces.ICrud;
import dao.model.UsuarioDao;
import dao.mysql.conexion.ConnectionMysql;

/**
 *
 * @author Jerson
 */

/*
  Patrón factory, nos permite crear instancias..
 */
public class DaoManager {
    
    //que base de datos queremos usar
    
    private DaoManager(){
    
    }
    
     public static ICrud getDaoManager(EDaoManager type){
         IConnection conn = ConnectionMysql.getInstance();
         switch(type){
             case DAO_USUARIO:
                 UsuarioDao userDao = new UsuarioDao(conn);
                 return userDao;
         }
         return null;
     }
             
    
}
