package ws;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import model.Data;
import model.Message;
import javax.websocket.Session;
import model.User;

/**
 *
 * @author Jerson
 */
@ServerEndpoint("/chatsocket")
public class ComunicationNode {

    private static final Set<Session> s = Collections.synchronizedSet(new HashSet<Session>());
    private static final Gson JSON = new Gson();
    
    @OnMessage
    public void onMessage(String message,Session session) {
        
        Data dat = JSON.fromJson(message,Data.class);
        Message msg = dat.getMessage();
        User us = dat.getUser();
        System.out.println(msg);
        System.out.println(us);
        
        switch(msg.getAction()){
            case "open":
                //verificamos que no exista una sesión iniciada
                Map<String,Object> map = session.getUserProperties();
                map.put("user",us);//llenamos la propiedad de los usuarios :D
                mostrarUsuarios();
                break;
            case "message":
                Map<String,Object> m = session.getUserProperties();//usuario que envía el mensaje
                User from = (User)m.get("user");
                try {
                    session.getBasicRemote().sendText(message);
                    System.out.println(us.getIdPerson()+" envia ");
                }catch(IOException e){}
                s.forEach(user ->{
                    System.out.println(((User)user.getUserProperties().get("user")).getIdPerson());
                    if(((User)user.getUserProperties().get("user")).getIdPerson()
                            ==msg.getIdUsuario()){
                        System.out.println(msg.getIdUsuario()+" destino");
                        try {
                            user.getBasicRemote().sendText(message);
                        } catch (IOException ex) {}
                    }
                });
                break;
            case "focus-writing":
                s.forEach(user ->{
                    if(((User)user.getUserProperties().get("user")).getIdPerson()
                            ==msg.getIdUsuario()){
                        try {
                            user.getBasicRemote().sendText(message);
                        } catch (IOException ex) {}
                    }
                });
                break;
            case "blur-writing":
                s.forEach(user ->{
                    if(((User)user.getUserProperties().get("user")).getIdPerson()
                            ==msg.getIdUsuario()){
                        try {
                            user.getBasicRemote().sendText(message);
                        } catch (IOException ex) {}
                    }
                });
                break;
        }
        
    }
    
    private String aux;

    @OnOpen
    public void onOpen(Session session) {
        s.add(session);
    }
    @OnError
    public void onError(Throwable t) {
    }

    @OnClose
    public void onClose(Session session) {
        s.remove(session);
        User us = (User)session.getUserProperties().get("user");
        Message ms = new Message(0, us.getIdPerson(), 0, "Cerró sesión", new Date(), "disconect");
        Data dat = new Data();
        dat.setMessage(ms);
        dat.setUser(us);
        dat.setUsers(allUsers());
        s.forEach(user ->{
            try {
                user.getBasicRemote().sendText(JSON.toJson(dat));
            } catch (IOException ex) {}
        });
        
    }
    
    private List<User> allUsers(){
        List<User> users = new ArrayList<>();
        s.forEach(user->{
            Map<String,Object> map = user.getUserProperties();
            users.add((User)map.get("user"));
        });
        return users;
    }
    
    private void mostrarUsuarios(){
        List<User> users = allUsers();
        //enviamos los usuarios disponibles a todos
        Data dat = new Data();
        dat.setMessage(new Message(0, 0, 0, "listado users", null, "listar"));
        dat.setUsers(users);
        s.forEach(user->{
            try {
                user.getBasicRemote().sendText(JSON.toJson(dat));
            } catch (IOException ex) {ex.printStackTrace();}
        });
    }
    
    
}
