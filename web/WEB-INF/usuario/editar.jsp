<%-- 
    Document   : editar.jsp
    Created on : 13/09/2019, 07:22:09 PM
    Author     : Jerson
--%>

<%@page import="model.Profile"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/pages/perfil/edit.css" rel="stylesheet" type="text/css"/>
<%
    User us = (User) request.getSession().getAttribute("user");
    Profile pr = (Profile) request.getAttribute("profile");
%>
<div class="je-container">
    <div class="je-item main-edit">
        <h1 class="t1 title-edit">Editar Perfil</h1>
        <form id="formulario-update">
            <!--<input type="hidden" name="accion" value="actualizar">-->
            <div class="form-group">
                <div class="form-item">
                    <label for="nombreU">Nombre:</label>
                    <input type="text" id="nombreU" name="name" required="true" value=<%=pr.getName()%>>
                </div>
                <div class="form-item">
                    <label for="apellidoU">Apellido:</label>
                    <input type="text" id="apellidoU" name="lastname" required="true" value=<%=pr.getLastname()%>>
                </div>
            </div>
                
             <div class="form-group">
                <div class="form-item">
                    <label for="usernameU">Username:</label>
                    <input type="text" id="usernameU" name="username" required="true" value=<%=us.getUsername()%>>
                </div>
                <div class="form-item">
                    <label for="emailU">Email:</label>
                    <input type="text" id="emailU" name="email" required="true" value=<%=us.getEmail()%>>
                </div>
            </div>   
            
            <div class="form-group">
                <div class="form-item">
                    <label for="tw"><i style="margin-right:.3rem;" class="fab fa-twitter"></i>Twitter: </label>
                    <input type="text" id="tw" name="tw" value=<%=pr.getTwitter() != null ? pr.getTwitter() : ""%>>
                </div>
                <div class="form-item">
                    <label for="fb"><i style="margin-right:.3rem;" class="fab fa-facebook-f"></i>Facebook: </label>
                    <input type="text" id="fb" name="fb" value=<%=pr.getTwitter() != null ? pr.getFacebook() : ""%>>
                </div>
            </div>
            <div class="form-group">
                <div class="form-item">
                    <label for="ins"><i style="margin-right:.3rem;" class="fab fa-instagram"></i>Instagram: </label>
                    <input type="text" id="ins" name="ins" value=<%=pr.getInstagram() != null ? pr.getInstagram() : ""%>>
                </div>
                <div class="form-item">
                    <label for="yt"><i style="margin-right:.3rem;" class="fab fa-youtube"></i>Youtube: </label>
                    <input type="text" id="yt" name="yt" value=<%=pr.getYoutube() != null ? pr.getYoutube() : ""%>>
                </div>
            </div>
            <div class="form-item">
                <label for="img"><i style="margin-right:.3rem;" class="fab fas fa-user-circle"></i>Imagen de perfil: </label>
                <input type="file" name="img" id="imgprofile">
            </div>
            <div class="form-item descripcion-profile">
                <label for="description">Descripción</label>
                <textarea name="description" id="description"><%=pr.getDescription() != null ? pr.getDescription() : ""%></textarea>
                <span class="count-desc circle" id="count-desc"><%=pr.getDescription() != null ? pr.getDescription().length() : 0%></span>
            </div>
            <!--<div class="form-item">
                <label for="edad">Edad: </label>
                <select name="contry">
                    <option value="perú"> Perú</option>
                    <option value="Colombia"> Colombia</option>
                    <option value="chile"> Chile</option>
                    <option value="Uruguay"> Uruguay</option>
                    <option value="Venezuela"> Venezuela</option>
                    <option value="Usa">Estados Unidos</option>
                </select>
                <input type="text" id="edad" name="edad" required="true">
            </div>
            -->
            <div class="form-item">
                <input type="submit" class="button" name="accion" value="Actualizar">
            </div>
        </form>
    </div>
</div>
<script src="https://kit.fontawesome.com/56e0c4d4ed.js"></script>
