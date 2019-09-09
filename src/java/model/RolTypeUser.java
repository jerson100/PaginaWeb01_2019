package model;

/**
 *
 * @author Jerson
 */
public class RolTypeUser {
    private int idTypeUser;
    private int idRol;

    public RolTypeUser(int idTypeUser, int idRol) {
        this.idTypeUser = idTypeUser;
        this.idRol = idRol;
    }

    public int getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(int idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}
