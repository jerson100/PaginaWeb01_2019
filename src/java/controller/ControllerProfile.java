package controller;

import utils.JeFile;
import com.google.gson.Gson;
import dao.enums.EDaoManager;
import dao.exceptions.AllException;
import dao.exceptions.CreateException;
import dao.exceptions.ReadException;
import dao.exceptions.UpdateException;
import dao.interfaces.ICrud;
import dao.manager.DaoManager;
import dao.model.LikeDao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Country;
import model.Like;
import model.Post;
import model.Profile;
import model.TypeUser;
import model.User;
import utils.Validator;

/**
 *
 * @author Jerson
 */
@MultipartConfig(maxFileSize = 10485760, fileSizeThreshold = 5242880, maxRequestSize = 20971520)
@WebServlet(name = "ControllerProfile", urlPatterns = {"/perfil"})
public class ControllerProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        if (request.getParameter("accion") != null) {
            switch (request.getParameter("accion")) {
                case "editar":
                    if (request.getSession().getAttribute("user") != null) {//verificamos que tenga una sesión iniciada
                        editarProfile(request, response);
                    }else{
                        response.sendRedirect("login");
                    }
                    break;
                default:
                    response.sendRedirect("");
            }
        } else {*/
        //obtenemos el id del usuario
        String id = request.getParameter("id");
        if (id != null) {
            if (Validator.validateNumber(id)) {
                verProfile(Integer.parseInt(id), request, response);
            } else {
                response.sendRedirect("");
            }
        } else {
            response.sendRedirect("");
        }
        // }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//para obtener todo en utf8
        if (request.getParameter("accion") != null) {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect("login");
            } else {
                switch (request.getParameter("accion")) {
                    case "editar":
                        editarProfile(request, response);
                        break;
                    case "publicar":
                        try {
                            publicar(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "actualizar":
                        actualizar(request, response);
                        break;
                    default:
                        response.setContentType("application/json;charset=UTF-8");
                        PrintWriter print = response.getWriter();
                        Gson gson = new Gson();
                        Map<String, Object> map = new HashMap<>();
                        map.put("estado", false);
                        print.println(gson.toJson(map));
                }
            }
        } else {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter print = response.getWriter();
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<>();
            map.put("estado", false);
            print.println(gson.toJson(map));
        }

    }

    private void editarProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //obtemos el profile del user

            ICrud<Profile, Integer> dao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);

            Profile p = dao.read(((User) request.getSession().getAttribute("user")).getIdPerson());

            request.setAttribute("profile", p);
            request.getRequestDispatcher("WEB-INF/usuario/editar.jsp").forward(request, response);
        } catch (ReadException ex) {
            Logger.getLogger(ControllerProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verProfile(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //verificamos si existe ese usuario en la base de datos
        ICrud<Profile, Integer> profileDao = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);
        ICrud<User, Integer> userDao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
        ICrud<TypeUser, Integer> typeDao = DaoManager.getDaoManager(EDaoManager.DAO_TYPE_USER);
        ICrud<Country, Integer> countryDao = DaoManager.getDaoManager(EDaoManager.DAO_COUNTRY);
        ICrud<Post, Integer> postDao = DaoManager.getDaoManager(EDaoManager.DAO_POST);

        Profile p = null;
        User us = null;
        TypeUser tu = null;
        Country ct = null;
        List<Post> allPost = null;
        List<Integer> allLikePost = null;

        try {

            //Falta verificar el estado del perfil del usuario
            //si está activo, podemos acceder a ese perfil,
            //si no está activo no podemos acceder a ese perfil
            //si está bloqueado tampoco podemos acceder a ese perfil, pero necesitamos decir a los que tratar de acceder que ese perfil está bloqueado
            p = profileDao.read(id);
            us = userDao.read(p.getIdUser());
            tu = typeDao.read(us.getIdTypeUser());
            ct = countryDao.read(p.getIdCountry());

            try {
                allPost = postDao.all(p.getIdUser());
                //si se encuentra post disponibles para ese perfil
                //entonces tenemos que verificar que post 
                Object objUserSession = request.getSession().getAttribute("user");
                if (objUserSession != null) {
                    //consultamos en la bd
                    //a que publicaciones el usuario con la sessión activa
                    //a dado like de perfil p
                    try {
                        ICrud<Like, Integer> likeDao = DaoManager.getDaoManager(EDaoManager.DAO_LIKE);
                        allLikePost = ((LikeDao) likeDao)
                                .getPostUserFromUserLike(
                                        ((User) objUserSession)
                                                .getIdPerson(),
                                        p.getIdUser());
                    } catch (AllException e) {/*e.printStackTrace();*/
                    }
                }
            } catch (AllException ex) {
                //ex.printStackTrace();
            }

            request.setAttribute("profile", p);
            request.setAttribute("user", us);
            request.setAttribute("typeUser", tu);
            request.setAttribute("country", ct);
            request.setAttribute("posts", allPost);
            request.setAttribute("postsUserLike", allLikePost);

            //enviamos los recursos a perfil.jsp
            request.getRequestDispatcher("WEB-INF/usuario/perfil.jsp").forward(request, response);
        } catch (ReadException e) {
            e.printStackTrace();
            response.sendRedirect("");
        }
    }
//comment
    private void publicar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part img = request.getPart("img");
        System.out.println(img);
        String titulo = request.getParameter("titulo");
        System.out.println("titulo: " + titulo);
        String msg = "";
        boolean estado = false;
        String url = "";
        ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_POST);

        if (titulo != null && !titulo.isEmpty()) {
            if (img != null && img.getSubmittedFileName() != null) {
                if (img.getSize() > 0) {
                    InputStream input = img.getInputStream();
                    String path = "imgs/post/";
                    String s = getServletContext().getRealPath("/" + path);
                    String urlPost = getName(titulo, img.getSubmittedFileName(), request.getSession());
                    if (guardarImagenDeProdructoEnElSistemaDeFicheros(input, s + "/" + urlPost)) {
                        try {
                            Post post = new Post();
                            post.setIdUser(((User) request.getSession().getAttribute("user")).getIdPerson());
                            post.setTitle(String.valueOf(request.getParameter("titulo")));
                            post.setUrlImage(path + urlPost);
                            dao.create(post);
                            msg = "Imagen subida exitosamente";
                            estado = true;
                            url = getServletContext().getContextPath() + "/perfil?id=" + ((User) request.getSession().getAttribute("user")).getIdPerson();
                        } catch (CreateException ex) {
                            ex.printStackTrace();
                            msg = ex.getMessage();
                        }
                    } else {
                        msg = "La imagen no se pudo subir";
                    }
                }
            } else {
                msg = "Seleccione una imagen";
            }
        } else {
            msg = "Ingrese un título";
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter print = response.getWriter();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("estado", estado);
        map.put("mensaje", msg);
        map.put("url", url);
        print.println(gson.toJson(map));
    }

    public static boolean guardarImagenDeProdructoEnElSistemaDeFicheros(InputStream input, String fileName)
            throws ServletException {
        FileOutputStream output = null;
        boolean ok = false;
        try {
            output = new FileOutputStream(fileName);
            int leido = 0;
            leido = input.read();
            while (leido != -1) {
                output.write(leido);
                leido = input.read();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                output.flush();
                output.close();
                input.close();
                ok = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ok;
    }

    private String getName(String titulo, String fileName, HttpSession session) {

        long fecha = new Date().getTime();
        int id = ((User) session.getAttribute("user")).getIdPerson();

        return id + "-" + fecha + "-" + titulo + "-" + fileName;

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter print = response.getWriter();
        Gson json = new Gson();
        Map<String, Object> map = new HashMap<>();
        String msg = "No se pudo actualizar";
        boolean estado = false;
        boolean estadoImage = false;
        String url = "";
        String urldirectorioImagenUsuario = null;
        String urlImagenUser = null;
        User us = null;
        try {
            Part img = request.getPart("img");
            if (request.getSession().getAttribute("user") != null) {
                us = (User) request.getSession().getAttribute("user");
                InputStream input = null;
                System.out.println(img.getSubmittedFileName());
                if (img != null && img.getSubmittedFileName() != null && !img.getSubmittedFileName().isEmpty()) {
                    if (img.getSize() > 0) {
                        String file = img.getSubmittedFileName();
                        String extension;
                        if ((extension = JeFile.validarImagen(file)) != null) {
                            input = img.getInputStream();
                            String path = "imgs/users/";
                            String s = getServletContext().getRealPath("/" + path);
                            String urlPost = String.valueOf(us.getIdPerson());
                            estadoImage = true;
                            urldirectorioImagenUsuario = s + "/" + urlPost + "." + extension;
                            urlImagenUser = path + urlPost +"."+ extension;
                        } else {
                            msg = "Por favor seleccione una imagen con extensión png, jpg o jpeg";
                        }
                    }
                } else {
                    estadoImage = true;
                }

                if (estadoImage) {
                    //validamos la data del usuario
                    String name = request.getParameter("name");
                    String lastname = request.getParameter("lastname");
                    String email = request.getParameter("email");
                    String username = request.getParameter("username");
                    String fb = request.getParameter("fb");
                    String tw = request.getParameter("tw");
                    String ins = request.getParameter("ins");
                    String yt = request.getParameter("yt");
                    String descripcion = request.getParameter("description");
                    if ((msg = validarDataUser(name, lastname, email, username)).isEmpty()) {//validamos la data del usuario
                        //validamos la data del perfil  
                        ICrud dao = DaoManager.getDaoManager(EDaoManager.DAO_USER);
                        User obj_user = new User(us.getIdPerson(), username, us.getPass(), email, 1);
                        try {
                            dao.update(obj_user);
                            try {
                                ICrud daoP = DaoManager.getDaoManager(EDaoManager.DAO_PROFILE);
                                Profile p = new Profile();
                                p.setAge(20);//por ahora lo dejamos así más adelante modificaremos esto
                                p.setDescription(descripcion);
                                p.setFacebook(fb);
                                p.setIdCountry(1);//por defecto, más adelante lo modificaremos
                                p.setIdUser(us.getIdPerson());
                                p.setInstagram(ins);
                                p.setLastname(lastname);
                                p.setName(name);
                                p.setState(1);
                                p.setTwitter(tw);
                                p.setYoutube(yt);
                                if (input != null) {//se eligió una imagen
                                    p.setUrlImage(urlImagenUser);
                                }
                                daoP.update(p);
                                //si todo va bien hasta ese momento, entonces subimos la imagen al directorio
                                if(input!=null){
                                    guardarImagenDeProdructoEnElSistemaDeFicheros(input, urldirectorioImagenUsuario);
                                    request.getSession().setAttribute("urlProfile",urlImagenUser);
                                }
                                msg = "Perfil actualizado";
                                estado = true;
                                url = getServletContext().getContextPath()+"/perfil?id="+us.getIdPerson();
                            } catch (UpdateException e) {
                                e.printStackTrace();
                                msg = e.getMessage();
                            }
                        } catch (UpdateException e) {
                            e.printStackTrace();
                            msg = "El usuario ya extiste";
                        }
                    }
                }

            } else {
                msg = "Primero inicie sesión";
                url = getServletContext().getContextPath() + "/login";
            }
        } catch (ServletException ex) {
            Logger.getLogger(ControllerProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        map.put("estado", estado);
        map.put("mensaje", msg);
        map.put("url", url);
        print.print(json.toJson(map));
    }

    private String validarData(String name, String lastName, String email,
            String username, String pass, String passR, boolean validarP) {

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

        if (validarP) {
            if (pass == null || !Validator.validatePassword(pass)) {
                return "La contraseña debe contener como mínimo 1 letra minúscula,1 mayúzcula, 1 número y tener entre {8,10}";
            }

            if (passR == null || !pass.equals(passR)) {
                return "Las contraseñas no coinciden";
            }
        }
        return "";
    }

    private String validarDataUser(String name, String lastname, String email, String username) {
        return validarData(name, lastname, email, username, null, null, false);
    }

}
