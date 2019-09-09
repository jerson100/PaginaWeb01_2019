package controller;

import dao.enums.EDaoManager;
import dao.exceptions.CreateException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Profile;
import model.User;
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
        request.getRequestDispatcher("WEB-INF/usuario/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
          Obtenemos los datos enviados del formulario
         */
        String accion = request.getParameter("accion");
        String name = request.getParameter("name");
        String last_name = request.getParameter("last-name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String passR = request.getParameter("passR");

        String msg = validarData(name, last_name, email, username, pass, passR);
        if (!msg.equals("")) {
            request.getSession().setAttribute("mensaje", msg);
            response.sendRedirect("registro");
            return;
        }

        ICrud userDao = DaoManager.getDaoManager(EDaoManager.DAO_USUARIO);
        ICrud profileDao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);

        switch (accion) {
            case "registar_usuario":
                //usuario comnún es = 2
                try {
                    User us = new User(username, pass, 2, 4,email);
                    userDao.create(us);
                    System.out.println(us.getIdPerson());
                    Profile pr = new Profile();
                    pr.setName(name);
                    pr.setLastname(last_name);
                    pr.setIdUser(us.getIdPerson());
                    profileDao.create(pr);
                } catch (CreateException ex){
                    ex.printStackTrace();
                    msg = ex.getMessage();
                }
                break;
        }
     
        if(!msg.equals("")){
            request.getSession().setAttribute("mensaje", msg);
            response.sendRedirect("registro");
        }else{
            
            /*
              Redireccionamos en el cliente al login
            */
            response.sendRedirect("login");
        }
        
    }
    
    

    private String validarData(String name, String lastName, String email,
            String username, String pass, String passR) {

        if (name==null||!Validator.validateLetter(name)) {
            return "El nombre no es válido";
        }

        if (lastName==null||!Validator.validateLetter(lastName)) {
            return "El apellido no es válido";
        }

        if (email==null||!Validator.validateEmail(email)) {
            return "El correo no es válido";
        }

        if (username==null||!Validator.validateLetterAndNumber(username)) {
            return "El username es incorrecto";
        }

        if (pass==null||!Validator.validatePassword(pass)) {
            return "La contraseña es incorrecta";
        }

        if (passR==null||!pass.equals(passR)) {
            return "Las contraseñas no coinciden";
        }

        return "";

    }

}
