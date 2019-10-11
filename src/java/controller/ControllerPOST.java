/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.enums.EDaoManager;
import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import dao.model.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Like;
import model.User;
import utils.Validator;

/**
 *
 * @author Jerson
 */
@MultipartConfig
@WebServlet(name = "ControllerPOST", urlPatterns = {"/post"})
public class ControllerPOST extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        System.out.println(accion);
        if (accion != null) {
            switch (accion) {
                case "ver":
                    verPost(request, response);
                    break;
                case "listarlikes":
                    verUsersLikePost(request, response);
                    break;
            }
        } else {
            response.sendRedirect("");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void verPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String id = request.getParameter("id");
        boolean estado = false;
        String msg = "";
        String url = url = getServletContext().getContextPath() + "/login";
        System.out.println("id: " + id);
        //Preguntamos si hay una sesión iniciada, si no la hay 
        //en el cliente redireccionamos al login
        User us = null;
        if (request.getSession().getAttribute("user") != null) {
            us = (User) request.getSession().getAttribute("user");
            if (id != null && Validator.validateNumber(id)) {
                try {
                    ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_LIKE);
                    Like l = new Like();
                    l.setIdPost(Integer.parseInt(id));
                    l.setIdUser(us.getIdPerson());
                    dao.create(l);
                    estado = true;
                    url = "";
                    msg = "Like";
                } catch (CreateException ex) {
                    ex.printStackTrace();
                    msg = ex.getMessage();
                }
            } else {
                msg = "La publicación no existe";
            }
        } else {
            msg = "Inicie sesión para poder dar like a una pulicación";
        }

        PrintWriter print = response.getWriter();
        Gson json = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", msg);
        map.put("estado", estado);
        map.put("url", url);
        print.print(json.toJson(map));
    }

    private void verUsersLikePost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");        
        String idPost = request.getParameter("id");
        String msg = "";
        boolean estado = false;
        List<User> users=null;
        if (idPost != null && Validator.validateNumber(idPost)) {
            try {
                ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
                users = ((UsuarioDao) dao).allUserLikePost(Integer.parseInt(idPost));
                estado = true;
            } catch (AllException ex) {
                msg = ex.getMessage();
            }
        } else {
            msg = "No existe esa publicación";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", msg);
        map.put("estado",estado);
        map.put("usuarios", users);
        try {
            PrintWriter print = response.getWriter();
            Gson json = new Gson();
            print.println(json.toJson(map));
        } catch (IOException ex) {}
    }

}
