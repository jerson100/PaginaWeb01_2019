package dao.interfaces;

import dao.exceptions.AccesDeneg;
import dao.exceptions.AllException;
import java.util.List;
import model.User;

/**
 *
 * @author Jerson
 */
/*Aquí podemos definir métodos específicos para clases que lo implementen,
en este caso la clase UserDao*/
public interface IUsuario extends ICrud<User, Integer>{
    User login(String username,String password) throws AccesDeneg;
    List<User> allUserLikePost(int idPost) throws AllException;
    List<User> lastRegisteredUsers(int count) throws AllException;
}
