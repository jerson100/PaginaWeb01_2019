package controller;

import dao.enums.EDaoManager;
import dao.exceptions.ReadException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Profile;
import utils.Validator;

/**
 *
 * @author Jerson
 */
@WebServlet(name = "ControllerProfile", urlPatterns = {"/perfil"})
public class ControllerProfile extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtenemos el id del usuario
        String id = request.getParameter("id");
        
        if(id!=null){
            
            if(Validator.validateNumber(id)){
                
                //verificamos si existe ese usuario en la base de datos
                
                ICrud<Profile,Integer> profileDao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);
                
                Profile p = null;
                
                try {
                    p = profileDao.read(Integer.parseInt(id));
                    request.setAttribute("profile", p);
                    request.getRequestDispatcher("WEB-INF/usuario/perfil.jsp").forward(request, response);
                } catch(ReadException e) {
                    response.sendRedirect("");
                }
                
            }else{
                response.sendRedirect("");
            }
            
        }else{
           response.sendRedirect("");
        }
        
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
