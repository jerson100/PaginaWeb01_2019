package model;

/**
 *
 * @author Jerson
 */
public class State {
    
    private int idState;
    private String name;
    private String description;

    public State(int idState, String name, String description) {
        this.idState = idState;
        this.name = name;
        this.description = description;
    }

    public State(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
