package controller;

import dao.enums.EDaoManager;
import dao.exceptions.ReadException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Country;
import model.Profile;
import model.TypeUser;
import model.User;
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

        if (request.getParameter("accion") != null) {

            request.getRequestDispatcher("WEB-INF/usuario/editar.jsp").forward(request, response);
            
        } else {

            //obtenemos el id del usuario
            String id = request.getParameter("id");

            if (id != null) {

                if (Validator.validateNumber(id)) {

                    //verificamos si existe ese usuario en la base de datos
                    ICrud<Profile, Integer> profileDao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);
                    ICrud<User, Integer> userDao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
                    ICrud<TypeUser, Integer> typeDao = DaoManager.getDaoManager(EDaoManager.DAO_TYPE_USER);
                    ICrud<Country, Integer> countryDao = DaoManager.getDaoManager(EDaoManager.DAO_COUNTRY);

                    Profile p = null;
                    User us = null;
                    TypeUser tu = null;
                    Country ct = null;

                    try {

                        //Falta verificar el estado del perfil del usuario
                        //si está activo, podemos acceder a ese perfil,
                        //si no está activo no podemos acceder a ese perfil
                        //si está bloqueado tampoco podemos acceder a ese perfil, pero necesitamos decir a los que tratar de acceder que ese perfil está bloqueado
                        p = profileDao.read(Integer.parseInt(id));
                        us = userDao.read(p.getIdUser());
                        tu = typeDao.read(us.getIdTypeUser());
                        ct = countryDao.read(us.getIdTypeUser());

                        request.setAttribute("profile", p);
                        request.setAttribute("user", us);
                        request.setAttribute("typeUser", tu);
                        request.setAttribute("country", ct);

                        //enviamos los recursos a perfil.jsp
                        request.getRequestDispatcher("WEB-INF/usuario/perfil.jsp").forward(request, response);
                    } catch (ReadException e) {
                        response.sendRedirect("");
                    }

                } else {
                    response.sendRedirect("");
                }

            } else {
                response.sendRedirect("");
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        
    }

}
