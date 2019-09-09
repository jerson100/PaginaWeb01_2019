package controller;

import dao.enums.EDaoManager;
import dao.exceptions.AccesDeneg;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import dao.model.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

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
        
        String username  = request.getParameter("username");
        String password  = request.getParameter("password");
        ICrud usuarioDao = DaoManager.getDaoManager(EDaoManager.DAO_USUARIO);
        
        try {
            User us = ((UsuarioDao)usuarioDao).login(username, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", us);
            response.sendRedirect("/ProyectoWeb01");
        } catch (AccesDeneg ex) {
            request.getSession().setAttribute("mensaje", ex.getMessage());
            response.sendRedirect("login");
        }
        
        
        
    }

}
