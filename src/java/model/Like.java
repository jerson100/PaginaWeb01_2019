package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
//como si fuera la clase like
public class Like {
    private int idPost;
    private int idUser;
    private Date datePostU;
    private byte state;

    public Like(){};
    
    public Like(int idPost, int idUser) {
        this.idPost = idPost;
        this.idUser = idUser;
    }

    public Like(int idPost, int idUser, Date datePostU, byte state) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.datePostU = datePostU;
        this.state = state;
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

    public Date getDatePostU() {
        return datePostU;
    }

    public void setDatePostU(Date datePostU) {
        this.datePostU = datePostU;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

}
