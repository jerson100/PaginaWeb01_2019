package controller;

import com.google.gson.Gson;
import dao.enums.EDaoManager;
import dao.exceptions.CreateException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        request.getSession().removeAttribute("url");
        request.getRequestDispatcher("WEB-INF/usuario/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControllerPOST.class.getName()).log(Level.SEVERE, null, ex);
                }
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            response.sendRedirect("");
        } else {
            switch (accion) {
                case "registrar_usuario":
                    //usuario comnún es = 2
                    registrarUsuario(request, response);
                    break;
                default:
                    response.sendRedirect("");
            }
        }

    }

    private String validarData(String name, String lastName, String email,
            String username, String pass, String passR) {

        if (name == null || !Validator.validateLetter(name)) {
            return "El nombre no es válido";
        }

        if (lastName == null || !Validator.validateLetter(lastName)) {
            return "El apellido no es válido";
        }

        if (email == null || !Validator.validateEmail(email)) {
            return "El correo no es válido, formato necesario: .@.com";
        }

        if (username == null || !Validator.validateLetterAndNumber(username)) {
            return "El username es incorrecto";
        }

        if (pass == null || !Validator.validatePassword(pass)) {
            return "La contraseña debe contener como mínimo 1 letra minúscula,1 mayúzcula, 1 número y tener entre {8,10}";
        }

        if (passR == null || !pass.equals(passR)) {
            return "Las contraseñas no coinciden";
        }

        return "";
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String name = request.getParameter("name");
        String last_name = request.getParameter("last-name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String passR = request.getParameter("passR");

        String msg = validarData(name, last_name, email, username, pass, passR);
        String url="";
        boolean create = false;
        
        Map<String, Object> map = new HashMap<>();
        Gson json = new Gson();
        PrintWriter print = response.getWriter();
        
        if (msg.isEmpty()) {
            try {
                //validamos
                ICrud userDao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
                ICrud profileDao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);
                User us = new User(0, username, pass, 2, 4, email);
                userDao.create(us);
                Profile pr = new Profile();
                pr.setUrlImage("imgs/users/profile.jpg");
                pr.setName(name);
                pr.setLastname(last_name);
                pr.setIdUser(us.getIdPerson());
                profileDao.create(pr);
                url = getServletContext().getContextPath()+"/login";
                create = true;
                msg = "Registro completado satisfactoriamente";
            } catch (CreateException ex) {
                ex.printStackTrace();
                msg = ex.getMessage();
            }
        }
        map.put("mensaje", msg);
        map.put("url",url);
        map.put("creado", create);
        print.println(json.toJson(map));
    }

}
