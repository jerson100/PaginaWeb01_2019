package controller;

import com.google.gson.Gson;
import dao.enums.EDaoManager;
import dao.exceptions.AccesDeneg;
import dao.exceptions.ReadException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import dao.model.ProfileDao;
import dao.model.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Profile;
import model.User;
import utils.States;

/**
 *
 * @author Jerson
 */
@WebServlet(name = "ControllerLogin", urlPatterns = {"/login"})
public class ControllerLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson json = new Gson();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ICrud usuarioDao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
        ICrud perfilDao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);

        Map<String,Object> dat = new HashMap<>();
        
        String msg;
        boolean acc;
        String url=null;
        try {
            User us = ((UsuarioDao) usuarioDao).login(username, password);
            if (States.LOCKED == us.getIdState()) {
                //request.getSession().setAttribute("mensaje", "El usuario se encuentra bloqueado");
                //response.sendRedirect("login");
                msg =  "Su cuenta se encuentra bloqueado";
                acc = false;
            }else {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", us);
                String urlProfile = null;
                try {
                    urlProfile = ((ProfileDao)perfilDao).getImageProfile(us.getIdPerson());
                } catch (ReadException e) {
                }
                session.setAttribute("urlProfile", urlProfile);//obtener la imagen de la bd del usuario
                //response.sendRedirect("/ProyectoWeb01");
                msg =  "Bienvenido";
                acc = true;
                String urlprev = (String)session.getAttribute("url");//url prev
                if(urlprev!=null){
                    url = urlprev;
                    session.removeAttribute("url");
                }else{
                    url = "";
                }
            }
        } catch (AccesDeneg ex) {
            //request.getSession().setAttribute("mensaje", ex.getMessage());
            //response.sendRedirect("login");
            msg = "Verifique su cuenta porfavor";
            acc = false;
        }
        dat.put("mensaje", msg);
        dat.put("acceso", acc);
        dat.put("url", url);
        out.println(json.toJson(dat));
    }

}
