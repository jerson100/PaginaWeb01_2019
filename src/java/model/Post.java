package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
public class Post {
    private int idPost;
    private Date date;
    private String title;
    private String description;
    private int idUsuario;
    private String urlPhoto;
    private int idState;

    public Post(int idPost, Date date, String title, String description, int idUsuario, String urlPhoto, int idState) {
        this.idPost = idPost;
        this.date = date;
        this.title = title;
        this.description = description;
        this.idUsuario = idUsuario;
        this.urlPhoto = urlPhoto;
        this.idState = idState;
    }

    public Post(Date date, String title, String description, int idUsuario, String urlPhoto, int idState) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.idUsuario = idUsuario;
        this.urlPhoto = urlPhoto;
        this.idState = idState;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    
    
    
}
