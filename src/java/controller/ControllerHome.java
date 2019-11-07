package controller;

import dao.enums.EDaoManager;
import dao.exceptions.AllException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import dao.model.PostDao;
import dao.model.UsuarioDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.User;
import utils.Pagination;

/**
 *
 * @author Jerson
 */
@WebServlet(name = "ControllerHome", urlPatterns = {"/home"})
public class ControllerHome extends HttpServlet {

    private static final int COUNTXPOST = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //obtenemos todas las publicaciones por ahora, más adelante tendremos que 
        //realizar una paginación para mejor rendimiento de la página web, okey!
        
        ICrud daoPost = DaoManager.getDaoManager(EDaoManager.DAO_POST);
        ICrud daoUser = DaoManager.getDaoManager(EDaoManager.DAO_USER);
        List<Post> list = null;
        List<User> listUser = null;
        
        //obtenemos el total de registros.
        int countRegister = ((PostDao)daoPost).countRegister();
        Pagination<Post> pagination = new Pagination<>();
        pagination.setCountXpage(COUNTXPOST);
        pagination.setCount_page(countRegister);
        pagination.setCurrent_page(1);
        pagination.setFirst_page(1);
        int resto = countRegister % COUNTXPOST;
        pagination.setLast_page(countRegister/COUNTXPOST + (resto>0?1:0));
        
        try {
            list = ((PostDao)daoPost).all(0,COUNTXPOST);
            listUser = ((UsuarioDao)daoUser).lastRegisteredUsers(10);
        } catch (AllException e) {}
        
        pagination.setData(list);
        
        //request.setAttribute("posts", list);
        request.setAttribute("pagination", pagination);
        request.setAttribute("lastUsers", listUser);
        
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
