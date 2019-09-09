package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
public class Like {

    private int idLike;
    private Date date;
    private int idPost;
    private int idUser;

    public Like(int idLike, Date date, int idPost, int idUser) {
        this.idLike = idLike;
        this.date = date;
        this.idPost = idPost;
        this.idUser = idUser;
    }

    public Like(Date date, int idPost, int idUser) {
        this.date = date;
        this.idPost = idPost;
        this.idUser = idUser;
    }

    public int getIdLike() {
        return idLike;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}
