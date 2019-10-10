package dao.interfaces;

import dao.exceptions.AllException;
import java.util.List;
import model.Like;

/**
 *
 * @author Jerson
 */
public interface ILike extends ICrud<Like, Integer>{
    List<Integer> getPostUserFromUserLike(int idUserLike,int idUserPost) throws AllException; 
}
