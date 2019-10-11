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
                                            <div class="card_footer_fecha"><span class="small"><%=JeDate.FORMAT.format(objP.getDatePost())%></span></div>
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
        <footer>
            <div class="je-container container-footer">
                <div class="je-item">
                    <div class="footer-location">
                        <h2 class="t4">Jerson Ramírez Ortiz</h2>
                        <p class="location-conuntry"> <span class="icon-contry"><i class="fas fa-globe-europe"></i>Huacho - Perú</span></p>
                        <p class="location-university">Unjfsc</p>
                    </div>
                </div>
                <div class="je-item">
                    <div class="footer-logo"><svg class="svg-footer" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid meet" viewBox="478 126 799 370"><g data-item-type="shape" data-item="Shape" data-logo-item="" id="logo__item--shape_1" class="logo__item logo__item--active">
                        <g class="logo__item__inner" transform="translate(421.95000076293945 106.5708999633789) scale(4.198380632663179 4.198380632663179) rotate(0 0 0)">
                        <g>
                        <g>
                        <g>
                        <g>
                        <path fill="#00BFF0" d="M62.8,88.1c-1.8-0.6-3.6-0.1-3.9,1c-0.3,1.1,0.8,2.5,2.7,3c1.8,0.6,5-0.7,5-0.7S64.6,88.6,62.8,88.1z" data-part-id="logo__item--shape_1__0"></path>
                        <path fill="#00BFF0" d="M59.1,83.2c-1,1.2-1.2,2.7-0.4,3.3c0.8,0.6,2.2,0.1,3.2-1.1c1-1.2,1-4,1-4S60.1,82,59.1,83.2z" data-part-id="logo__item--shape_1__1"></path>
                        <path fill="#00BFF0" d="M64,81.4c-0.6,1.4-0.4,2.9,0.4,3.3c0.9,0.4,2.1-0.4,2.8-1.9c0.6-1.4-0.1-4.2-0.1-4.2S64.7,79.9,64,81.4z" data-part-id="logo__item--shape_1__2"></path>
                        <path fill="#00BFF0" d="M69.3,78.3c-0.6,1.5-0.3,2.9,0.6,3.3c0.9,0.4,2.1-0.6,2.7-2c0.6-1.5-0.3-4.1-0.3-4.1S69.9,76.9,69.3,78.3z" data-part-id="logo__item--shape_1__3"></path>
                        <path fill="#00BFF0" d="M74,74.8c-0.3,1.4,0.2,2.6,1.1,2.7c0.8,0.2,1.7-0.8,2-2.2c0.3-1.4-0.9-3.5-0.9-3.5S74.3,73.4,74,74.8z" data-part-id="logo__item--shape_1__4"></path>
                        <path fill="#00BFF0" d="M77.5,70.9c-0.1,1.3,0.4,2.3,1.2,2.4c0.8,0.1,1.5-0.9,1.6-2.1c0.1-1.3-1.1-3.2-1.1-3.2S77.6,69.6,77.5,70.9z" data-part-id="logo__item--shape_1__5"></path>
                        <path fill="#00BFF0" d="M79.9,66.8c0.1,1.1,0.7,1.9,1.4,1.8c0.7-0.1,1.1-1,1-2.1c-0.1-1.1-1.5-2.4-1.5-2.4S79.8,65.7,79.9,66.8z" data-part-id="logo__item--shape_1__6"></path>
                        <path fill="#00BFF0" d="M81.4,62.5c0.1,1.1,0.7,1.9,1.4,1.8c0.7-0.1,1.1-1,1-2.1c-0.1-1.1-1.4-2.5-1.4-2.5S81.3,61.4,81.4,62.5z" data-part-id="logo__item--shape_1__7"></path>
                        <path fill="#00BFF0" d="M68.8,86.4c-1.9-0.2-3.5,0.5-3.7,1.7c-0.1,1.2,1.3,2.3,3.2,2.5c1.9,0.2,4.8-1.6,4.8-1.6S70.7,86.7,68.8,86.4z" data-part-id="logo__item--shape_1__8"></path>
                        <path fill="#00BFF0" d="M73.7,83.3c-1.9,0.2-3.3,1.3-3.2,2.4c0.1,1.2,1.7,2,3.6,1.8c1.9-0.2,4.3-2.5,4.3-2.5S75.6,83.1,73.7,83.3z" data-part-id="logo__item--shape_1__9"></path>
                        <path fill="#00BFF0" d="M78.1,79.5c-1.5,0.6-2.4,1.8-2.1,2.7c0.4,0.9,1.9,1.2,3.3,0.6c1.5-0.6,2.9-3.1,2.9-3.1S79.6,78.9,78.1,79.5z" data-part-id="logo__item--shape_1__10"></path>
                        <path fill="#00BFF0" d="M81.3,74.4c-1.4,0.9-2.1,2.3-1.5,3.2c0.5,0.9,2.1,0.9,3.5,0c1.4-0.9,2.4-3.7,2.4-3.7S82.7,73.6,81.3,74.4z" data-part-id="logo__item--shape_1__11"></path>
                        <path fill="#00BFF0" d="M84,69.5c-1.2,0.9-1.6,2.2-1.1,2.9c0.6,0.7,2,0.6,3.1-0.3c1.2-0.9,1.8-3.5,1.8-3.5S85.2,68.6,84,69.5z" data-part-id="logo__item--shape_1__12"></path>
                        <path fill="#00BFF0" d="M85.4,65c-0.9,0.8-1.2,1.9-0.7,2.5c0.5,0.6,1.6,0.3,2.5-0.5c0.9-0.8,1.2-3,1.2-3S86.3,64.2,85.4,65z" data-part-id="logo__item--shape_1__13"></path>
                        <path fill="#00BFF0" d="M85.3,61.2c-0.7,1-0.7,2.1-0.1,2.5c0.6,0.4,1.7,0,2.3-1c0.7-1,0.5-3.1,0.5-3.1S86,60.2,85.3,61.2z" data-part-id="logo__item--shape_1__14"></path>
                        <path fill="#00BFF0" d="M84.1,62.4c0.1,3.7-1.6,7.2-3.6,10.2c-2.1,3-4.7,5.7-7.5,8c-2.9,2.2-6.1,4-9.5,5.3c-3.4,1.3-7,2-10.7,1.8l0,0.1      c3.6,0.9,7.5,0.3,11.1-0.9c3.6-1.2,6.8-3.3,9.7-5.7c2.9-2.4,5.4-5.2,7.4-8.3c1-1.6,1.8-3.3,2.5-5c0.6-1.8,1-3.6,0.8-5.5H84.1z" data-part-id="logo__item--shape_1__15"></path>
                        </g>
                        </g>
                        <g>
                        <g>
                        <path fill="#00BFF0" d="M39.9,88.1c1.8-0.6,3.6-0.1,3.9,1c0.3,1.1-0.8,2.5-2.7,3c-1.8,0.6-5-0.7-5-0.7S38.1,88.6,39.9,88.1z" data-part-id="logo__item--shape_1__16"></path>
                        <path fill="#00BFF0" d="M43.6,83.2c1,1.2,1.2,2.7,0.4,3.3c-0.8,0.6-2.2,0.1-3.2-1.1c-1-1.2-1-4-1-4S42.6,82,43.6,83.2z" data-part-id="logo__item--shape_1__17"></path>
                        <path fill="#00BFF0" d="M38.6,81.4c0.6,1.4,0.5,2.9-0.4,3.3c-0.9,0.4-2.1-0.4-2.8-1.9c-0.6-1.4,0.1-4.2,0.1-4.2S38,79.9,38.6,81.4z" data-part-id="logo__item--shape_1__18"></path>
                        <path fill="#00BFF0" d="M33.4,78.3c0.6,1.5,0.3,2.9-0.6,3.3c-0.9,0.4-2.1-0.6-2.7-2c-0.6-1.5,0.3-4.1,0.3-4.1S32.8,76.9,33.4,78.3z" data-part-id="logo__item--shape_1__19"></path>
                        <path fill="#00BFF0" d="M28.6,74.8c0.3,1.4-0.2,2.6-1.1,2.7c-0.8,0.2-1.7-0.8-2-2.2c-0.3-1.4,0.9-3.5,0.9-3.5S28.4,73.4,28.6,74.8z" data-part-id="logo__item--shape_1__20"></path>
                        <path fill="#00BFF0" d="M25.2,70.9c0.1,1.3-0.4,2.3-1.2,2.4c-0.8,0.1-1.5-0.9-1.6-2.1c-0.1-1.3,1.1-3.2,1.1-3.2S25.1,69.6,25.2,70.9z" data-part-id="logo__item--shape_1__21"></path>
                        <path fill="#00BFF0" d="M22.7,66.8c-0.1,1.1-0.7,1.9-1.4,1.8c-0.7-0.1-1.1-1-1-2.1c0.1-1.1,1.5-2.4,1.5-2.4S22.9,65.7,22.7,66.8z" data-part-id="logo__item--shape_1__22"></path>
                        <path fill="#00BFF0" d="M21.3,62.5c-0.1,1.1-0.7,1.9-1.4,1.8c-0.7-0.1-1.1-1-1-2.1c0.1-1.1,1.4-2.5,1.4-2.5S21.4,61.4,21.3,62.5z" data-part-id="logo__item--shape_1__23"></path>
                        <path fill="#00BFF0" d="M33.8,86.4c1.9-0.2,3.5,0.5,3.6,1.7c0.1,1.2-1.3,2.3-3.1,2.5c-1.9,0.2-4.8-1.6-4.8-1.6S31.9,86.7,33.8,86.4z" data-part-id="logo__item--shape_1__24"></path>
                        <path fill="#00BFF0" d="M29,83.3c1.9,0.2,3.3,1.3,3.2,2.4c-0.1,1.2-1.7,2-3.6,1.8c-1.9-0.2-4.3-2.5-4.3-2.5S27.1,83.1,29,83.3z" data-part-id="logo__item--shape_1__25"></path>
                        <path fill="#00BFF0" d="M24.6,79.5c1.5,0.6,2.4,1.8,2.1,2.7c-0.4,0.9-1.9,1.2-3.3,0.6c-1.5-0.6-2.9-3.1-2.9-3.1S23.1,78.9,24.6,79.5z" data-part-id="logo__item--shape_1__26"></path>
                        <path fill="#00BFF0" d="M21.3,74.4c1.4,0.9,2.1,2.3,1.5,3.2c-0.5,0.9-2.1,0.9-3.5,0C18,76.7,17,73.9,17,73.9S20,73.6,21.3,74.4z" data-part-id="logo__item--shape_1__27"></path>
                        <path fill="#00BFF0" d="M18.6,69.5c1.2,0.9,1.6,2.2,1.1,2.9c-0.6,0.7-2,0.6-3.1-0.3c-1.2-0.9-1.8-3.5-1.8-3.5S17.5,68.6,18.6,69.5z" data-part-id="logo__item--shape_1__28"></path>
                        <path fill="#00BFF0" d="M17.3,65c0.9,0.8,1.2,1.9,0.7,2.5c-0.5,0.6-1.6,0.3-2.5-0.5c-0.9-0.8-1.2-3-1.2-3S16.4,64.2,17.3,65z" data-part-id="logo__item--shape_1__29"></path>
                        <path fill="#00BFF0" d="M17.4,61.2c0.7,1,0.7,2.1,0.1,2.5c-0.6,0.4-1.7,0-2.3-1c-0.7-1-0.5-3.1-0.5-3.1S16.7,60.2,17.4,61.2z" data-part-id="logo__item--shape_1__30"></path>
                        <path fill="#00BFF0" d="M18.5,62.4c-0.1,3.7,1.6,7.2,3.6,10.2c2.1,3,4.7,5.7,7.5,8c2.9,2.2,6.1,4,9.5,5.3c3.4,1.3,7,2,10.7,1.8l0,0.1      c-3.7,0.9-7.6,0.3-11.1-0.9c-3.6-1.2-6.8-3.3-9.7-5.7c-2.9-2.4-5.4-5.2-7.4-8.3c-1-1.6-1.8-3.3-2.5-5c-0.6-1.8-1-3.6-0.8-5.5      H18.5z" data-part-id="logo__item--shape_1__31"></path>
                        </g>
                        </g>
                        </g>
                        <g>
                        <path fill="#00BFF0" d="M50.9,77.2c-33-19-28.8-54.8-28.8-55.2L22,21.7L51,9.1V5.4L18.7,19.3c0,0-4.7,40.2,32.3,61.8v-3.7L50.9,77.2z" data-part-id="logo__item--shape_1__32"></path>
                        <path fill="#00BFF0" d="M23.1,22.6C22.7,26.3,21,58.3,51,75.9V10.5L23.1,22.6z" data-part-id="logo__item--shape_1__33"></path>
                        <path fill="#00BFF0" d="M78.9,22.6L51,10.5v65.4C82,58.3,79.3,26.3,78.9,22.6z" data-part-id="logo__item--shape_1__34"></path>
                        <path fill="#00BFF0" d="M83.3,19.3L51,5.4v3.7l29.3,12.6l0.1,0.4c0,0.4,4,36.2-29,55.2L51,77.4v3.7C88,59.6,83.3,19.3,83.3,19.3z" data-part-id="logo__item--shape_1__35"></path>
                        </g>
                        </g>
                        </g>
                        </g><g data-item-type="text" data-item="Business" id="logo__item--business" class="logo__item">
                        <g class="logo__item__inner" transform="translate(812.8515625 408.3957167870051) scale(9.944122575173827 9.944122575173827) rotate(0 0 0)">
                        <text data-part-id="logo__item--business" dy="0" dominant-baseline="auto" alignment-baseline="auto" font-family="Roboto Slab" font-size="32px" fill="#006ba8" letter-spacing="0" font-weight="normal" font-style="normal" data-font-family="Roboto Slab" data-font-weight="normal" data-font-style="normal" data-ttf-url="/builder_assets/fontsttf/font-montserrat-bold-normal.ttf" data-font-filename="roboto-slab-normal-normal">I</text>
                        </g>
                        </g><g data-item-type="text" id="logo__item--tagline_1" data-item="Tagline" class="logo__item">
                        <g class="logo__item__inner" transform="translate(550 332) scale(2.287397343362241 2.287397343362241) rotate(0 0 0)">
                        <text dy="0" dominant-baseline="auto" alignment-baseline="auto" font-family="Roboto Slab" font-size="18px" fill="#fff" letter-spacing="1" data-font-family="Roboto Slab" data-font-weight="bold" data-font-style="normal" font-weight="bold" font-style="normal" data-ttf-url="/builder_assets/fontsttf/font-open-sans-normal-normal.ttf" data-part-id="logo__item--tagline_1__0" data-font-filename="roboto-slab-bold-normal">UNJFSC</text>
                        </g>
                        </g><g data-item-type="text" id="logo__item--tagline_2" data-item="Tagline" class="logo__item">
                        <g class="logo__item__inner" transform="translate(910 366) scale(10.346796974153193 10.346796974153193) rotate(0 0 0)">
                        <text dy="0" dominant-baseline="auto" alignment-baseline="auto" font-family="Roboto Slab" font-size="18px" fill="#757575" letter-spacing="1" data-font-family="Roboto Slab" data-font-weight="normal" data-font-style="normal" font-weight="normal" font-style="normal" data-ttf-url="/builder_assets/fontsttf/font-open-sans-normal-normal.ttf" data-part-id="logo__item--tagline_2__0" data-font-filename="roboto-slab-normal-normal">TEC</text>
                        </g>
                        </g><g data-item-type="text" id="logo__item--tagline_3" data-item="Tagline" class="logo__item">
                        <g class="logo__item__inner" transform="translate(586 257) scale(2.3781111642065236 2.3781111642065236) rotate(0 0 0)">
                        <text dy="0" dominant-baseline="auto" alignment-baseline="auto" font-family="Roboto Slab" font-size="18px" fill="#fff" letter-spacing="1" data-font-family="Roboto Slab" data-font-weight="bold" data-font-style="normal" font-weight="bold" font-style="normal" data-ttf-url="/builder_assets/fontsttf/font-open-sans-normal-normal.ttf" data-part-id="logo__item--tagline_3__0" data-font-filename="roboto-slab-bold-normal">FIISI</text>
                        </g>
                        </g></svg>
                    </div>
                </div>
                <div class="je-item">
                    <div class="footer-contact">
                        <h2 class="t4">Contacto</h2>
                        <ul class="list-contact">
                            <li class="contact-item"><a href=""><i class="fab fa-facebook-f"></i></a></li>
                            <li class="contact-item"><a href=""><i class="fab fa-twitter"></i></a></li>
                            <li class="contact-item"><a href=""><i class="fab fa-instagram"></i></a></li>
                            <li class="contact-item"><a href=""><i class="fab fa-youtube"> </i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
        <script src="js/toogle.js"></script>
        <script>toogleButton('icon', 'navigation', 'active')</script>
        <script src="js/vistaCards.js"></script>
        <script src="js/utils/modal.js" type="text/javascript"></script>
        <script>
            openModalImg("publicaciones");
        </script>
        <script src="js/ajax.js" type="text/javascript"></script>
        <script src="js/view-profile-like.js" type="text/javascript"></script>
        <%if (request.getSession().getAttribute("user") != null && us.getIdPerson()==
                ((User)request.getSession().getAttribute("user")).getIdPerson()) {%>
        <script src="js/form.js" type="text/javascript"></script>
        <script src="js/view-proile-editar.js" type="text/javascript"></script>
        <script src="js/view-profile-publicar.js" type="text/javascript"></script>
        <script src="js/view-profile-update.js.js" type="text/javascript"></script>
        <%}%>
        <script src="https://kit.fontawesome.com/56e0c4d4ed.js"></script>
        <script src="js/utils/elements.js" type="text/javascript"></script>
    </body>
</html>