package model;

/**
 *
 * @author Jerson
 */
public class TypeUser {

    private int idTipoUsuario;
    private String type;
    private String urlIcon;

    public TypeUser(int idTipoUsuario, String type, String urlIcon) {
        this.idTipoUsuario = idTipoUsuario;
        this.type = type;
        this.urlIcon = urlIcon;
    }

    public TypeUser(String type, String urlIcon) {
        this.type = type;
        this.urlIcon = urlIcon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }
    
   
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getTipo() {
        return type;
    }

    public void setTipo(String tipo) {
        this.type = tipo;
    }

}
