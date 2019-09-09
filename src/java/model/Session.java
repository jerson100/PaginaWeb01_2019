package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
public class Session {

    private int idSession;
    private Profile Profile;
    private Date DateSession;

    public Session(Profile Profile, Date DateSession) {
        this.Profile = Profile;
        this.DateSession = DateSession;
    }

    public Session(int idSession, Profile Profile, Date DateSession) {
        this.idSession = idSession;
        this.Profile = Profile;
        this.DateSession = DateSession;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public Profile getProfile() {
        return Profile;
    }

    public void setProfile(Profile Profile) {
        this.Profile = Profile;
    }

    public Date getDateSession() {
        return DateSession;
    }

    public void setDateSession(Date DateSession) {
        this.DateSession = DateSession;
    }

    
    
}
