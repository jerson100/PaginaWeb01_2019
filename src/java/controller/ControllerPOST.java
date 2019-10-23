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
import dao.model.ComentarioDao;
import dao.model.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Comentario;
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
        request.getSession().removeAttribute("url");
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
                case "detallePost":
                    verDetallePost(request, response);
                    break;
                case "comment":
                    comentar(request, response);
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
        String url = getServletContext().getContextPath() + "/login";
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

    private void verDetallePost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        String idPost = request.getParameter("id");
        String msg = "";
        boolean estado = false;
        //List<User> users = null;
        List<Comentario> comments = null;
        if (idPost != null && Validator.validateNumber(idPost)) {

            try {
                ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_Comentarios);
                comments = dao.all(Integer.parseInt(idPost));
                estado = true;
                /*ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
                users = ((UsuarioDao) dao).allUserLikePost(Integer.parseInt(idPost));
                estado = true;*/
            } catch (AllException ex) {
                msg = ex.getMessage();
            }
        } else {
            msg = "No existe esa publicación";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", msg);
        map.put("estado", estado);
        map.put("allComments", comments);
        try {
            PrintWriter print = response.getWriter();
            Gson json = new Gson();
            print.println(json.toJson(map));
        } catch (IOException ex) {
        }
    }

    private void comentar(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        String idPost = request.getParameter("id");
        String texto = request.getParameter("texto");
        String msg = "";
        boolean estado = false;
        User us = null;
        String url = "";
        if (idPost != null && Validator.validateNumber(idPost)) {

            //validamos si hay una sesión iniciada
            //de lo contrario lo redireccionamos al inicio
            HttpSession se = (HttpSession) request.getSession();

            if ((User) se.getAttribute("user") == null) {
                
                url = getServletContext().getContextPath() + "/login";
                
                msg = "Inicie sesión para poder comentar";

            } else {

                try {
                    ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_Comentarios);
                    Comentario c = new Comentario();
                    c.setIdPost(Integer.parseInt(idPost));
                    c.setTexto(texto);
                    User usS = (User) request.getSession().getAttribute("user");
                    c.setUser(usS);
                    dao.create(c);
                    estado = true;
                    us = new User();
                    us.setIdPerson(usS.getIdPerson());
                    us.setIdTypeUser(usS.getIdTypeUser());
                    us.setUsername(usS.getUsername());
                    us.setUrl(usS.getUrl());
                } catch (CreateException e) {
                    msg = e.getMessage();
                }

            }

        } else {
            msg = "No existe esa publicación";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", msg);
        map.put("estado", estado);
        map.put("user", us);
        map.put("url", url);
        try {
            PrintWriter print = response.getWriter();
            Gson json = new Gson();
            print.println(json.toJson(map));
        } catch (IOException ex) {
        }
    }

}
