/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
