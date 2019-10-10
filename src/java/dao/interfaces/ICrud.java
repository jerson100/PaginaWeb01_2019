package dao.interfaces;

import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.DeleteException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import java.util.List;
import model.Post;

/**
 *
 * @author Jerson
 */
public interface ICrud<T,K> {
    void create(T o) throws CreateException;
    T read(K id) throws ReadException;
    boolean update(T o) throws UpdateException;
    boolean delete(K id) throws DeleteException;
    List<T> all() throws AllException;
    List<T> all(K id) throws AllException;
}
