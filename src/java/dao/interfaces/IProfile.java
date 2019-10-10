package dao.interfaces;

import dao.exceptions.ReadException;
import model.Profile;

/**
 *
 * @author Jerson
 */
public interface IProfile extends ICrud<Profile,Integer>{
    String getImageProfile(int id) throws ReadException;
}
