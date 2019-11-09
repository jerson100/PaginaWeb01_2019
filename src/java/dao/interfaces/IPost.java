package dao.interfaces;

import dao.exceptions.AllException;
import java.util.List;
import model.Post;

/**
 *
 * @author Jerson
 */
public interface IPost extends ICrud<Post,Integer>{
    List<Post> all(int i,int j) throws AllException;
    List<Post> all(int id,int i,int j) throws AllException;
    int countRegister();
}
