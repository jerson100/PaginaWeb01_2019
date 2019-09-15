package dao.manager;

import dao.enums.EDaoManager;
import dao.interfaces.IConnection;
import dao.interfaces.ICrud;
import dao.model.CountryDao;
import dao.model.ProfileDao;
import dao.model.TypeUserDao;
import dao.model.UsuarioDao;
import dao.mysql.conexion.ConnectionMysql;

/**
 *
 * @author Jerson
 */

/*
  Patrón factory, nos permite crear instancias..
  de un tipo, en consecuencia podemos usar el polimorfismo :v
 */
public class DaoManager {
    
    //que base de datos queremos usar
    
    private DaoManager(){
    
    }
    
     public static ICrud getDaoManager(EDaoManager type){
         IConnection conn = ConnectionMysql.getInstance();
         switch(type){
             case DAO_USER:
                 return new UsuarioDao(conn);
             case DAO_PROFILE:
                 return new ProfileDao(conn);
             case DAO_COUNTRY:
                 return new CountryDao(conn);
             case DAO_TYPE_USER:
                 return new TypeUserDao(conn);
         }
         return null;
     }
             
    
}
