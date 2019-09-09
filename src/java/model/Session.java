package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
public class Session {

    private int idSession;
    private int idProfile;
    private Date DateSession;

    public Session(int idSession, int idProfile, Date DateSession) {
        this.idSession = idSession;
        this.idProfile = idProfile;
        this.DateSession = DateSession;
    }

    public Session(int idProfile, Date DateSession) {
        this.idProfile = idProfile;
        this.DateSession = DateSession;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public Date getDateSession() {
        return DateSession;
    }

    public void setDateSession(Date DateSession) {
        this.DateSession = DateSession;
    }


    
    
}
