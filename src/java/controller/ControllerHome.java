package controller;

import dao.enums.EDaoManager;
import dao.exceptions.AllException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import dao.model.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.geometry.Pos;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.User;

/**
 *
 * @author Jerson
 */
@WebServlet(name = "ControllerHome", urlPatterns = {"/home"})
public class ControllerHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //obtenemos todas las publicaciones por ahora, más adelante tendremos que 
        //realizar una paginación para mejor rendimiento de la página web, okey!
        
        ICrud daoPost = DaoManager.getDaoManager(EDaoManager.DAO_POST);
        ICrud daoUser = DaoManager.getDaoManager(EDaoManager.DAO_USER);
        List<Post> list = null;
        List<User> listUser = null;
        try {
            list = daoPost.all();
            listUser = ((UsuarioDao)daoUser).lastRegisteredUsers(10);
        } catch (AllException e) {}
        
        request.setAttribute("posts", list);
        request.setAttribute("lastUsers", listUser);
        
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
