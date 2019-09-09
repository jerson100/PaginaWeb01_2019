package model;

/**
 *
 * @author Jerson
 */
public class Profile {

    private int idUser;
    private String urlImage;
    private int state;/*Me dice el estado del usuario*/
    private String name;
    private String lastname;
    private int age;
    private int idCountry;
    private String description;
    private String twitter;
    private String facebook;
    private String youtube;
    private String instagram;
    private String urlProfile;

    public Profile(String urlImage, int state, String name, String lastname, int age, int idCountry, String description, String twitter, String facebook, String youtube, String instagram, String urlProfile) {
        this.urlImage = urlImage;
        this.state = state;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.idCountry = idCountry;
        this.description = description;
        this.twitter = twitter;
        this.facebook = facebook;
        this.youtube = youtube;
        this.instagram = instagram;
        this.urlProfile = urlProfile;
    }

    public Profile(int idUser, String urlImage, int state, String name, String lastname, int age, int idCountry, String description, String twitter, String facebook, String youtube, String instagram, String urlProfile) {
        this.idUser = idUser;
        this.urlImage = urlImage;
        this.state = state;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.idCountry = idCountry;
        this.description = description;
        this.twitter = twitter;
        this.facebook = facebook;
        this.youtube = youtube;
        this.instagram = instagram;
        this.urlProfile = urlProfile;
    }

    public Profile() {
        
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    
    
    
}
