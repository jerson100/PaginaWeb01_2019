package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
public class Post {
    private int idPost;
    //private int idUser;//usuario que creó el post
    private User user;
    private String title;
    private String urlImage;
    private Date datePost;
    private byte idState;
    private int countLikes;

    public Post() {
    }
    
    public Post(int idPost, User user, String title, String urlImage, byte idState) {
        this.idPost = idPost;
        this.user = user;
        this.title = title;
        this.urlImage = urlImage;
        this.idState = idState;
    }

    public Post(int idPost, int idUser, String title, String urlImage, Date datePost, int countLikes) {
        this.idPost = idPost;
        this.user = user;
        this.title = title;
        this.urlImage = urlImage;
        this.datePost = datePost;
        this.countLikes = countLikes;
    }

    public Post(int idPost, User user, String title, String urlImage, Date datePost, byte idState, int countLikes) {
        this.idPost = idPost;
        this.user = user;
        this.title = title;
        this.urlImage = urlImage;
        this.datePost = datePost;
        this.idState = idState;
        this.countLikes = countLikes;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public byte getIdState() {
        return idState;
    }

    public void setIdState(byte idState) {
        this.idState = idState;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    
    
}
