package model;

import java.util.Date;

/**
 *
 * @author Jerson
 */
public class Message {
    private int idMessage;
    private int idUsuario;
    private int idSala;
    private String messageTxt;
    private Date date;
    private String action;

    public Message(int idMessage, int idUsuario, int idSala, String messageTxt, Date date, String action) {
        this.idMessage = idMessage;
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.messageTxt = messageTxt;
        this.date = date;
        this.action = action;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Message{" + "idMessage=" + idMessage + ", idUsuario=" + idUsuario + ", idSala=" + idSala + ", messageTxt=" + messageTxt + ", date=" + date + ", action=" + action + '}';
    }
    
}
