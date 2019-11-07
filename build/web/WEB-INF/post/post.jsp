<%-- 
    Document   : post
    Created on : 05/11/2019, 03:18:31 AM
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
        <title><c:out value="${requestScope.post.title}"/></title>
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
        <link href="css/components/comment.css" rel="stylesheet" type="text/css"/>
        <link href="css/pages/post/post.css" rel="stylesheet" type="text/css"/>
        <script src="js/toogle.js"></script>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"></jsp:include>
            <div class="user-banner-portada"><img src="imgs/jpg/banner.jpg" alt="imagen de portada"></div>
            <main>
                <div class="je-container container-main">
                    <div class="je-item">
                        <article class="article-post" id="post-<c:out value="${requestScope.post.idPost}"/>">
                            <header>
                                <div class="post-autor">
                                    <div class="post-autor_inner">
                                        <img class="post-autor_img" src="<c:out value="${requestScope.post.user.url}"/>" alt="<c:out value="${requestScope.post.user.username}"/>">
                                        <div class="post-autor_details">
                                            <h2><a href="perfil?id=<c:out value="${requestScope.post.user.idPerson}"/>"><c:out value="${requestScope.post.user.username}"/></a></h2>
                                            <jsp:useBean id="date" class="utils.JeDate"/>
                                            <span><c:out value="${date.getTime(requestScope.post.datePost)}"/></span>
                                        </div>
                                    </div>
                                </div>
                            </header>
                            <section class="section-details">
                                <header class="section-details_header">
                                    <h3 class="post-title"><c:out value="${requestScope.post.title}"/></h3>
                                    <img class="post-img" src="<c:out value="${requestScope.post.urlImage}"/>" alt="<c:out value="${requestScope.post.title}"/>">
                                </header>
                                <div class="seccion-details_body">
                                    <div class="sidebar sidebar-details">
                                        <div class="count-likes">
                                            <svg viewBox="0 0 17 15.736" class="svg-like" id="svg-like" xmlns="http://www.w3.org/2000/svg"> 
                                                <path d="M0,40.045H2.909V31.318H0Zm16-8a1.459,1.459,0,0,0-1.455-1.455H9.964l.727-3.345v-.218a1.525,1.525,0,0,0-.291-.8L9.6,25.5,4.8,30.3a1.22,1.22,0,0,0-.436,1.018v7.273a1.459,1.459,0,0,0,1.455,1.455h6.545a1.437,1.437,0,0,0,1.309-.873l2.182-5.164a1.238,1.238,0,0,0,.073-.509V32.045H16Z" transform="translate(0.5 -24.81)"></path>
                                            </svg>
                                            <span>
                                                <c:out value="${requestScope.post.countLikes}"/>
                                            </span>
                                        </div>
                                        <div class="count-comments">
                                            <span id="count-comment"><c:out value="${requestScope.comments.size()}"/> comentarios</span>
                                        </div>
                                    </div>
                                    <div class="sidebar sidebar-event">
                                        <button id="like" class="button">Me gusta</button>
                                        <button id="comentar" class="button">Comentar</button>
                                    </div>
                                </div>
                                <section class="section-comments">
                                    <header>
                                        <h4>Comentarios</h4>
                                    </header>
                                    <div class="container-users">
                                        <div class="comments-container">
                                            <ul id="comments-list" class="comments-list">
                                                <c:forEach items="${requestScope.comments}" var="comment">
                                                    <li class="list-level">
                                                        <div class="comment-main-level" id="comment-<c:out value="${comment.idComentario}"/>">
                                                            <!-- Avatar -->
                                                            <div class="card_autor_img comment-avatar">
                                                                <div class="card-user-type"><svg viewBox="0 0 16 16" class="svg-icon-admin" xmlns="http://www.w3.org/2000/svg"><g transform="translate(-97.103 -44.137)">
                                                                    <path d="M113.1,52.137a8,8,0,1,0-8,8,8,8,0,0,0,8-8" fill="#f0c419"></path>
                                                                    <path d="M155.4,88.276l1.7,3.434,3.63.566-2.578,2.673.8,3.549L155.4,96.951,151.847,98.5l.8-3.549-2.578-2.673,3.63-.566Z" transform="translate(-50.299 -41.917)" fill="#ffe69f"></path></g>
                                                                    </svg>
                                                                    <span class="user-type_name">adm</span>
                                                                </div>
                                                                <img class="" src="<c:out value="${comment.user.url}"/>" alt="<c:out value="${comment.user.username}"/>">
                                                            </div>
                                                            <!-- Contenedor del Comentario -->
                                                            <div class="comment-box">
                                                                <div class="comment-head">
                                                                    <a class="comment-name by-author" href="perfil?id=<c:out value="${comment.user.idPerson}"/>"><c:out value="${comment.user.username}"/></a>
                                                                    <span><c:out value="${comment.fecha}"/></span>
                                                                    <i class="fa fa-heart" aria-hidden="true"></i>
                                                                </div>
                                                                <div class="comment-content">
                                                                    <p>
                                                                        <c:out value="${comment.texto}"/>
                                                                    </p>
                                                                </div>
                                                                <button class="response button" style="
                                                                        color: white;
                                                                        background: #03658c;
                                                                        background: background: #03658c;
                                                                        /* padding: 12px; */
                                                                        margin-left: 12px;
                                                                        margin-bottom: 12px;
                                                                        ">
                                                                    responser
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="comment-post">
                                        <input id="comment" type="text" placeholder="comenta algo">
                                        <i class="fas fa-chevron-right" id="send-comment" aria-hidden="true"></i>
                                    </div>
                                </section>
                            </section>
                        </article>
                    </div>
                </div>
            </main>
        <%@include file="../includes/footer.jsp"%>
        <script src="js/ajax.js" type="text/javascript"></script>
        <script src="js/utils/modal.js" type="text/javascript"></script>
        <script src="js/view-like.js" type="text/javascript"></script>
        <script src="js/utils/elements.js" type="text/javascript"></script>
        <script src="js/send-comment.js" type="text/javascript"></script>
        <script src="js/utils/je-validate.js" type="text/javascript"></script>
        <script>
                let idText = document.querySelector(".article-post").id;
                let id;
                if(idText){
                    let indexSeparator = idText.lastIndexOf("-");
                    let idd = idText.substring(indexSeparator+1,idText.length);
                    if(validateNumber(idd)){
                        id = idd;
                        console.log(document.getElementById("svg-like"));
                        viewUserLike(id,document.getElementById("svg-like"));
                    }
                }
                
                //send comment
                let element = document.getElementById("send-comment");
                let comment = document.getElementById("comment");
                let countComment = document.getElementById("count-comment");
                let receive = document.getElementById("comments-list");
                sendComment(element,comment,id,countComment,receive);
        </script>
        <script>toogleButton('icon', 'navigation', 'active')</script>
        <script src="https://kit.fontawesome.com/56e0c4d4ed.js"></script>
    </body>
</html>
