<%-- 
    Document   : registro.jsp
    Created on : 08/09/2019, 07:37:09 PM
    Author     : Jerson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Home</title>
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
           if(request.getSession().getAttribute("mensaje")!=null)
           {
            out.println("<div>"+(String)request.getSession().getAttribute("mensaje")+"</div>");
            request.getSession().setAttribute("mensaje", null);
        }
        %>

    <main class="container-allPage">
        <div class="allPage-contains">
            <div class="allPage_header">
                <h1 class="small text-right">Registro</h1>
            </div>
            <div class="allPage_main"> 
                <form action="registro" method="post" name="form-registro">
                    <input type="hidden" name="accion" value="registar_usuario">
                    <div class="form-item">
                        <label for="name">Nombre:</label>
                        <input type="text" id="name" name="name">
                    </div>
                    <div class="form-item">
                        <label for="last-name">Apellidos:</label>
                        <input type="text" id="last-name" name="last-name">
                    </div>
                    <div class="form-item">
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email">
                    </div>
                    <div class="form-item">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username">
                    </div>
                    <div class="form-item">
                        <label for="pass">Password:</label>
                        <input type="password" id="pass" name="pass">
                    </div>
                    <div class="form-item">
                        <label for="pass-r">Repite la password:</label>
                        <input type="password" id="pass-r" name="passR">
                    </div>
                    <div class="form-item">
                        <input class="button center-button" type="submit" value="Registrarme">
                    </div>
                </form>
            </div>
            <div class="allPage_footer"></div>
        </div>
    </main>
</body>
</html>