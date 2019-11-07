<%-- 
    Document   : index
    Created on : 08/09/2019, 10:18:16 PM
    Author     : Jerson
--%>

<%@page import="utils.JeDate"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Post"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="initial-scale=1.0, width=device-width">
        <meta name="twitter:card" content="summary_large_image">
        <meta name="twitter:site" content="">
        <meta property="og:title" content="I love you">
        <meta property="og:description" content="Aquí les muestro mi portafolio">
        <meta property="og:image" content="">
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
        <link href="css/pages/home/home.css" rel="stylesheet" type="text/css"/>
        <link href="css/components/pagination.css" rel="stylesheet" type="text/css"/>
        <script src="js/toogle.js"></script>
    </head>
    <body>
        <%
            //List<Post> post = (List<Post>)request.getAttribute("posts");
            //List<User> lastUsers = (List<User>)request.getAttribute("lastUsers");
        %>
        <jsp:include page="includes/header.jsp"></jsp:include>
        <div class="user-banner-portada"><img src="imgs/jpg/banner.jpg" alt="imagen de portada"></div>
        <main>
            <div class="je-container container-main">
                <div class="je-item">
                    <section id="section-post">
                        <h2 class="title-articles">Últimas publicaciones</h2>
                        <div class="je-container container-post" id="container-post-a">
                        <c:forEach items="${pagination.data}" var="p">
                            <div class="je-item article-post">
                                <article class="card" id="post-<c:out value="${p.idPost}"/>">
                                    <header class="card-header">
                                        <a href="post?id=<c:out value="${p.idPost}"/>">
                                            <h3 class="card-header_title"><c:out value="${p.title}"/></h3>
                                            <div class="card-header_img">
                                                <img src="<c:out value="${p.urlImage}"/>" alt="<c:out value="${p.title}"/>" class="img-post">
                                            </div>
                                        </a>
                                    </header>
                                    <footer class="card-footer" class="card_post__info">
                                        <div class="card_autor" id="user-<c:out value="${p.user.idPerson}"/>">
                                            <img src="<c:out value="${p.user.url}"/>" alt="<c:out value="${p.user.username}"/>" class="card_autor__img img-user">
                                            <a href="perfil?id=<c:out value="${p.user.idPerson}"/>" class="card_autor__name"><c:out value="${p.user.username}"/></a>
                                        </div>
                                        <div class="card_fecha">
                                            <jsp:useBean id="date" class="utils.JeDate"/>
                                            <span><c:out value="${date.getTime(p.datePost)}"/></span>
                                        </div> 
                                    </footer>
                                </article>
                            </div>
                        </c:forEach>
                        </div>
                        <c:set var="pag" scope="page" value="${pagination}"/>
                        <div id="main-page" class="pagination">
                            <div class="p-left">
                                <button class="button" id="<c:out value="${pag.first_page}"/>"><</button>
                            </div>
                            <ul class="pagination_list">
                                <li class="pagination_item"><button class="button current-page">1</button></li>
                                <c:forEach var="j" begin="2" end="${pag.last_page}">  
                                    <li class="pagination_item">
                                        <button class="button"><c:out value="${j}"/></button>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="p-right">
                                <button class="button" id="<c:out value="${pag.last_page}"/>">></button>
                            </div>
                        </div>
                    </section>
                    <section>
                        <h2>
                            Otra cosa
                        </h2>
                    </section>
                </div>
                <div class="je-item">
                    <aside>
                        <h2 style="display: none;">Aside</h2>
                        <section class="section-lastUsers">
                            <h3>Últimos usuarios Registrados</h3>
                            <ol class="list-users">
                                <c:set var="count" scope="page" value="0"/>
                                <c:forEach items="${lastUsers}" var="u">
                                    <li class="item-user" id="item-user-<c:out value="${u.idPerson}"/>">
                                        <div class="item-user_info">
                                            <span class="count"><c:out value="${count = count + 1}"/></span>
                                        <img src="<c:out value="${u.url}"/>" alt="<c:out value="${u.username}"/>" class="img-user">
                                        <a class="username" href="perfil?id=<c:out value="${u.idPerson}"/>"><c:out value="${u.username}"/></a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ol> 
                        </section>
                        <section class="section-userPost-count">
                            <h3>Usuarios con más publicaciones</h3>
                            <ul>
                                <li>Juan</li>
                                <li>Pedro</li>
                                <li>Manuel</li>
                            </ul>
                        </section>
                    </aside>
                </div>
            </div>
        </main>
        <%@include file="includes/footer.jsp"%>
        <script>toogleButton('icon', 'navigation', 'active')</script>
        <script src="js/utils/je-validate.js" type="text/javascript"></script>
        <script src="js/ajax.js" type="text/javascript"></script>
        <script src="js/pagination.js" type="text/javascript"></script>
        <script>
            pagination("main-page","container-post-a");
        </script>
        <script src="https://kit.fontawesome.com/56e0c4d4ed.js"></script>
    </body>
</html>
