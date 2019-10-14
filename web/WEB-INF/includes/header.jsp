<%@page import="model.User"%>
<%
   User us =null; 
   String urlProfile = null;
   if(request.getSession().getAttribute("user")!=null){
       us = (User)request.getSession().getAttribute("user");
       System.out.println(us.getEmail()+" " +us.getIdPerson());
       urlProfile = (String)request.getSession().getAttribute("urlProfile");
   }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="main-header">
    <div class="je-container">
        <div class="je-item container-logo"><a class="logo" href="/ProyectoWeb01"><img src="imgs/svg/logo.svg" alt="logo de la página"></a></div>
        <div class="je-item container-navigation">
            <div class="je-container icon-menu-container">
                <div class="icon-menu" id="icon"></div>
            </div>
            <nav class="content-navigation" id="navigation">
                <h2 class="ocultar">Menú de navegación</h2>
                <ul class="menu-navigation je-item">
                    <li><a href="">Inicio</a></li>
                    <li><a href="html/lenguajes">Lenguajes</a></li>
                    <li><a href="html/publicaciones">Publicaciones</a></li>
                    <li><a href="html/conoceme">Conóceme</a></li>
                    <li>
                        <div class="container-perfil">
                            <%if(us==null){%>
                            <a href="login" style="padding: .65rem 0;" class="button ghost">Acceder</a>
                            <%}else{%>
                            <a class="logo" href="perfil?id=<%=us.getIdPerson()%>">
                                <img src=<%=urlProfile%> alt="perfil" style="height:2rem;width:2rem;">
                               </a>
                               <svg class="icon-flecha" viewBox="0 0 284.929 284.929" class="svg-icon icon-arrow" id="user-arrow" xmlns="http://www.w3.org/2000/svg">
                                  <path d="M282.082,76.511l-14.274-14.273c-1.902-1.906-4.093-2.856-6.57-2.856c-2.471,0-4.661,0.95-6.563,2.856L142.466,174.441L30.262,62.241c-1.903-1.906-4.093-2.856-6.567-2.856c-2.475,0-4.665,0.95-6.567,2.856L2.856,76.515C0.95,78.417,0,80.607,0,83.082c0,2.473,0.953,4.663,2.856,6.565l133.043,133.046c1.902,1.903,4.093,2.854,6.567,2.854s4.661-0.951,6.562-2.854L282.082,89.647c1.902-1.903,2.847-4.093,2.847-6.565C284.929,80.607,283.984,78.417,282.082,76.511z"></path> 
                                  <div class="user-acces" id="user-acces">
                                      <a href="perfil?id=<%=us.getIdPerson()%>" class="button ghost">Perfil</a>
                                      <a href="logout" class="button ghost">Salir</a>
                                  </div>
                               </svg>
                            <%}%>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>