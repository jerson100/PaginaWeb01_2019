<%-- 
    Document   : perfil
    Created on : 09/09/2019, 01:39:02 PM
    Author     : Jerson
--%>
<%@page import="utils.JeDate"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Post"%>
<%@page import="javafx.geometry.Pos"%>
<%@page import="model.Country"%>
<%@page import="model.TypeUser"%>
<%@page import="model.Profile"%>
<%@page import="model.User"%>

<%
    request.setCharacterEncoding("UTF-8");
    //obtenemos la información del perfil...
    User us = null;
    Profile p = null;
    TypeUser t = null;
    Country c = null;
    List<Post> post = null;
    List<Integer> postsUserLike = null;
    
    if (request.getAttribute("user") != null) {
        us = (User) request.getAttribute("user");
        p = (Profile) request.getAttribute("profile");
        t = (TypeUser) request.getAttribute("typeUser");
        c = (Country) request.getAttribute("country");
        post = (ArrayList<Post>) request.getAttribute("posts");
        postsUserLike = (ArrayList<Integer>)request.getAttribute("postsUserLike");
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title><%=us.getUsername()%></title>
        <meta charset="utf-8">
        <meta name="viewport" content="initial-scale=1.0, width=device-width">
        <meta name="twitter:card" content="summary_large_image">
        <meta name="twitter:site" content="">
        <meta property="og:title" content="Perfil">
        <meta property="og:description" content=<%=p.getDescription()%>>
        <meta property="og:image">
        <meta name="title" content=<%=us.getUsername()%>>
        <meta name="description" content=<%=p.getDescription()%>>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:400,400i,700|Open+Sans:700">
        <link rel="shortcut icon" href="imgs/svg/logo.svg">
        <link rel="stylesheet" href="css/base/base.css">
        <link rel="stylesheet" href="css/theme/colors.css">
        <link rel="stylesheet" href="css/theme/typography.css">
        <link rel="stylesheet" href="css/theme/fonts.css">
        <link rel="stylesheet" href="css/layout/layout.css">
        <link rel="stylesheet" href="css/components/buttons.css">
        <link rel="stylesheet" href="css/base/header.css">
        <link rel="stylesheet" href="css/base/footer.css">
        <link rel="stylesheet" href="css/pages/perfil/perfil.css">
        <link rel="stylesheet" href="css/components/modal.css">
        <link rel="stylesheet" href="css/components/formulario.css">
        <link href="css/components/loader.css" rel="stylesheet" type="text/css"/>
        <link href="css/components/modal.css" rel="stylesheet" type="text/css"/>
        <link href="css/components/comment.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"></jsp:include>
            <div class="user-banner-portada"><img src="imgs/jpg/banner.jpg" alt="imagen de portada"></div>
            <div class="loader-container" id="loader"></div>
            <main>
                <header class="ocultar">
                    <h1><%=us.getUsername()%></h1>
            </header>

            <div class="je-container main">
                <section class="je-item section-user">
                    <header>
                        <h2 class="ocultar">Descripción</h2>
                    </header>
                    <div class="user-dat user-img"><img src=<%=p.getUrlImage()%> alt=<%=p.getName() +""+p.getLastname()%>></div>
                    <p class="t3 grey-color-dark color"><%=us.getUsername()%></p>
                    <div class="user-dat user-email">
                        <p><%=us.getEmail()%></p>
                    </div>
                    <div class="user-dat user-description">
                        <p class="small">
                            <%=p.getDescription()%>
                        </p>
                    </div>
                    <div class="user-dat user-country">
                        <p class="small"> <span>♥ <span><%=c.getPais()%></span></span></p>
                    </div>
                    <div class="user-dat user-social">
                        <div class="user-social_web"><a class="smaller" href=""><span><a href="#">name.site.com</a></span></a></div>
                        <div class="user-red-social"><span>F G</span></div>
                    </div>
                    <%if (request.getSession().getAttribute("user") != null) {%>
                    <%if (us.getIdPerson() == ((User) request.getSession().getAttribute("user")).getIdPerson()) {%>
                    <button class="user-dat button ghost">Cambiar contraseña</button>
                    <button id="user-btn-editar" class="user-dat button">Editar</button>
                    <button class="user-dat button ghost" id="publicar">Publicar</button>
                    <%}%>
                    <%}%>
                </section>
                <section class="je-item section-publicaciones" id="publicaciones">
                    <header class="title-publicaciones">
                        <h2 class="t3">Publicaciones</h2>
                    </header>
                    <div class="post-nav">
                        <button class="button ghost" id="post-nav-grilla">Grilla </button>
                        <button class="button ghost" id="post-nav-column">Columna </button>
                    </div>
                    <div class="container-publicaciones" id="container-publicaciones">
                        <%if (post != null) {%>
                        <%for (Post objP : post) {%>
                        <div class="group-post">
                            <div class="container-card">
                                <article class="card_article">
                                    <header class="card_header">
                                        <h3 class="card_title t4"><a href="html/ejercicios_basicos/ejercicio1.html"><%=objP.getTitle()%></a></h3>
                                        <img class="card-img-post" src="<%=objP.getUrlImage()%>" alt="<%=objP.getTitle()%>"/>
                                        <div class="like-post">
                                            <span>
                                                <%=objP.getCountLikes()%>
                                            </span>
                                            <svg viewBox="0 0 17 15.736" class="svg-like" xmlns="http://www.w3.org/2000/svg" 
                                                 <%
                                                     if(postsUserLike!=null && postsUserLike.contains(objP.getIdPost())){
                                                         out.print("style='fill:#007BDF;'");
                                                     }
                                                 %>> 
                                                <path d="M0,40.045H2.909V31.318H0Zm16-8a1.459,1.459,0,0,0-1.455-1.455H9.964l.727-3.345v-.218a1.525,1.525,0,0,0-.291-.8L9.6,25.5,4.8,30.3a1.22,1.22,0,0,0-.436,1.018v7.273a1.459,1.459,0,0,0,1.455,1.455h6.545a1.437,1.437,0,0,0,1.309-.873l2.182-5.164a1.238,1.238,0,0,0,.073-.509V32.045H16Z" transform="translate(0.5 -24.81)"></path>
                                            </svg>
                                            <span style="display: none;"><%=objP.getIdPost()%></span>
                                        </div>
                                    </header>
                                    <footer class="card_footer">
                                        <div class="je-container card_footer_container">
                                            <div class="card_footer_autor">
                                                <div class="card-user">
                                                    <%if(us.getIdTypeUser()==1){%>
                                                    <div class="card-user-type"><svg viewBox="0 0 16 16" class="svg-icon-admin" xmlns="http://www.w3.org/2000/svg"><g transform="translate(-97.103 -44.137)">
                                                        <path d="M113.1,52.137a8,8,0,1,0-8,8,8,8,0,0,0,8-8" fill="#f0c419"></path>
                                                        <path d="M155.4,88.276l1.7,3.434,3.63.566-2.578,2.673.8,3.549L155.4,96.951,151.847,98.5l.8-3.549-2.578-2.673,3.63-.566Z" transform="translate(-50.299 -41.917)" fill="#ffe69f"></path></g>
                                                        </svg>
                                                        <span class="user-type_name">adm</span>
                                                    </div>
                                                    <%}%>
                                                    <img class="card_autor_img" src=<%=p.getUrlImage()%> alt=<%=p.getName()%>/>
                                                </div>
                                                <a class="card_autor_name underline small" href="html/jerson.html"><%=us.getUsername()%></a>
                                            </div>
                                            <div class="card_footer_fecha"><span class="small"><%=JeDate.getTime(objP.getDatePost())%></span></div>
                                        </div>
                                    </footer>
                                </article>
                            </div>
                            <div class="group-date"><span class="small">2019</span><span class="small">02/09</span></div>
                        </div>
                        <%}%>
                        <%} else {%>
                        <p>Aún no a publicado nada ♥</p>
                        <%}%>
                    </div>
                </section>
            </div>
        </main>
        <jsp:include page="../includes/footer.jsp"></jsp:include>
        <script src="js/view-profile-like.js" type="text/javascript"></script>
        <script src="js/toogle.js"></script>
        <script>toogleButton('icon', 'navigation', 'active')</script>
        <script>toogleButton('user-arrow' , 'user-acces', 'active-profile-user')</script>
        <script src="js/vistaCards.js"></script>
        <script src="js/utils/elements.js" type="text/javascript"></script>
        <script src="js/utils/modal.js" type="text/javascript"></script>
        <script src="js/ajax.js" type="text/javascript"></script>
        <script src="js/view-like.js" type="text/javascript"></script>
        <script src="js/send-comment.js" type="text/javascript"></script>
        <script src="js/profile.js" type="text/javascript"></script>
        <%if (request.getSession().getAttribute("user") != null && us.getIdPerson()==
                ((User)request.getSession().getAttribute("user")).getIdPerson()) {%>
        <script src="js/form.js" type="text/javascript"></script>
        <script src="js/view-proile-editar.js" type="text/javascript"></script>
        <script src="js/view-profile-publicar.js" type="text/javascript"></script>
        <script src="js/view-profile-update.js.js" type="text/javascript"></script>
        <%}%>
        <script src="https://kit.fontawesome.com/56e0c4d4ed.js"></script>
    </body>
</html>