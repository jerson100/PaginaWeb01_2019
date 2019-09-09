package dao.interfaces;

import dao.exceptions.AccesDeneg;
import model.Session;
import model.User;

/**
 *
 * @author Jerson
 */
/*Aquí podemos definir métodos específicos para clases que lo implementen,
en este caso la clase UserDao*/
public interface IUsuario extends ICrud<User, Integer>{
    User login(String username,String password) throws AccesDeneg;
}
