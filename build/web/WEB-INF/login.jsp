<%-- 
    Document   : login
    Created on : 08/09/2019, 08:31:16 PM
    Author     : Jerson
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, width=device-width">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:400,400i,700|Open+Sans:700">
    <link rel="shortcut icon" href="img/svg/logo.svg">
    <link rel="stylesheet" href="css/base/base.css">
    <link rel="stylesheet" href="css/theme/colors.css">
    <link rel="stylesheet" href="css/theme/typography.css">
    <link rel="stylesheet" href="css/theme/fonts.css">
    <link rel="stylesheet" href="css/layout/layout.css">
    <link rel="stylesheet" href="css/components/buttons.css">
    <link rel="stylesheet" href="css/pages/templateAbstract.css">
    <link rel="stylesheet" href="css/components/formulario.css">
  </head>
  <body>
      <%
          String msg = "";
          if(request.getSession().getAttribute("mensaje")!=null){
             msg = (String)request.getSession().getAttribute("mensaje");
          }
         %>
         <div class="login-msg <%out.println(!msg.equals("")?"active":"");%>">
             <%if(!msg.equals("")){%>
                 <%=(String)request.getSession().getAttribute("mensaje")%>
                 <%request.getSession().removeAttribute("mensaje");%>
             <%}%>
         </div>
    <main class="container-allPage">
      <div class="allPage-contains">
        <div class="allPage_header">
          <p class="small text-right">Inicia sesión</p>
        </div>
        <div class="allPage_main"> 
            <form action="login" method="post" name="formulario-login">
            <div class="form-item">
              <label for="username">Username:</label>
              <input type="text" id="username" name="username">
            </div>
            <div class="form-item">
              <label for="pass">Password:</label>
              <input type="password" id="password" name="password">
            </div>
            <div class="form-item">
              <input class="button center-button" type="submit" value="Ingresar">
            </div><a class="registry-user" href="registro">registrarme        </a>
          </form>
        </div>
        <div class="allPage_footer"></div>
      </div>
    </main>
  </body>
</html>