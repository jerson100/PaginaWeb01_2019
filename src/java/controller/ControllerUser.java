package controller;

import dao.enums.EDaoManager;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Validator;

/**
 *
 * @author Jerson
 */
@WebServlet(name = "ControllerUser", urlPatterns = {"/registro"})
public class ControllerUser extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*
          Obtenemos los datos enviados del formulario
        */
        String accion = request.getParameter("accion");
        String name = request.getParameter("name");
        String last_name = request.getParameter("lastname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String passR = request.getParameter("passR");
        
        ICrud usuarioDao = DaoManager.getDaoManager(EDaoManager.DAO_USUARIO);
        String msg = validarData(name, last_name, email, username, pass, passR);
        if(!msg.equals("")){
            request.setAttribute("mensaje",msg);
            request.getRequestDispatcher("").forward(request, response);
            return;
        }
        
        switch(accion){
            case "registar_usuario":
              
            break;
        }
        
    }
    
    private String validarData(String name,String lastName,String email,
                             String username,String pass,String passR){
        
        if(!Validator.validateLetter(name)){
           return "El nombre no es válido";
        }
        
        if(!Validator.validateLetter(lastName)){
            return "El apellido no es válido";
        }
        
        if(!Validator.validateEmail(email)){
           return "El correo no es válido";
        }
        
        if(!Validator.validateLetter(username)){
            return "El username es incorrecto";
        }
        
        if(!Validator.validatePassword(pass)){
            return "La contraseña es incorrecta";
        }
        
        if(!pass.equals(passR)){
            return "Las contraseñas no coinciden";
        }
        
        return "";
        
    }
    
}
