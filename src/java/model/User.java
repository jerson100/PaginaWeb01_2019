package model;

/**
 *
 * @author Jerson
 */
public class User{

    private int idPerson;
    private String username;
    private String pass;
    private int idTypeUser;
    private int idState;
    private String email;

    public User(int idPerson, String username, String pass, int idTypeUser, int idState,String email) {
        this.idPerson = idPerson;
        this.username = username;
        this.pass = pass;
        this.idTypeUser = idTypeUser;
        this.idState = idState;
        this.email = email;
    }

    public User(String username, String pass, int idTypeUser, int idState,String email) {
        this.username = username;
        this.pass = pass;
        this.idTypeUser = idTypeUser;
        this.idState = idState;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(int idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }
    
    
    
    
}
