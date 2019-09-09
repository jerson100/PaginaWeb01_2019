package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.User;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("    <head>\n");
      out.write("        <title>I love you</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"initial-scale=1.0, width=device-width\">\n");
      out.write("        <meta name=\"twitter:card\" content=\"summary_large_image\">\n");
      out.write("        <meta name=\"twitter:site\" content=\"\">\n");
      out.write("        <meta property=\"og:title\" content=\"I love you\">\n");
      out.write("        <meta property=\"og:description\" content=\"Aquí les muestro mi portafolio\">\n");
      out.write("        <meta property=\"og:image\" content=\"\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700|Open+Sans:700\">\n");
      out.write("        <link rel=\"shortcut icon\" href=\"imgs/svg/logo.svg\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/base/base.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/theme/colors.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/theme/typography.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/theme/fonts.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/layout/layout.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/components/buttons.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/base/header.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/base/footer.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/pages/perfil/perfil.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/components/modal.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/components/formulario.css\">\n");
      out.write("        <script src=\"js/toogle.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header class=\"main-header\">\n");
      out.write("            <div class=\"je-container\">\n");
      out.write("                <div class=\"je-item container-logo\"><a class=\"logo\" href=\"/\"><img src=\"imgs/svg/logo.svg\" alt=\"logo de la página\"></a></div>\n");
      out.write("                <div class=\"je-item container-navigation\">\n");
      out.write("                    <div class=\"je-container icon-menu-container\">\n");
      out.write("                        <div class=\"icon-menu\" id=\"icon\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <nav class=\"content-navigation\" id=\"navigation\">\n");
      out.write("                        <h2 class=\"ocultar\">Menú de navegación</h2>\n");
      out.write("                        <ul class=\"menu-navigation je-item\">\n");
      out.write("                            <li><a href=\"/\">Inicio</a></li>\n");
      out.write("                            <li><a href=\"html/lenguajes\">Lenguajes</a></li>\n");
      out.write("                            <li><a href=\"html/publicaciones\">Publicaciones</a></li>\n");
      out.write("                            <li><a href=\"html/conoceme\">Conóceme</a></li>\n");
      out.write("                            <li>\n");
      out.write("                                <div class=\"container-perfil\"><a class=\"logo\"><img src=\"imgs/jpg/yop.jpg\" alt=\"perfil\" style=\"height:2rem;\"></a><svg class=\"icon-flecha\" viewBox=\"0 0 284.929 284.929\" class=\"svg-icon icon-arrow\" id=\"user-arrow\" xmlns=\"http://www.w3.org/2000/svg\"> <path d=\"M282.082,76.511l-14.274-14.273c-1.902-1.906-4.093-2.856-6.57-2.856c-2.471,0-4.661,0.95-6.563,2.856L142.466,174.441L30.262,62.241c-1.903-1.906-4.093-2.856-6.567-2.856c-2.475,0-4.665,0.95-6.567,2.856L2.856,76.515C0.95,78.417,0,80.607,0,83.082c0,2.473,0.953,4.663,2.856,6.565l133.043,133.046c1.902,1.903,4.093,2.854,6.567,2.854s4.661-0.951,6.562-2.854L282.082,89.647c1.902-1.903,2.847-4.093,2.847-6.565C284.929,80.607,283.984,78.417,282.082,76.511z\"></path> </svg></div>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                    </nav>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <div class=\"user-banner-portada\"><img src=\"imgs/jpg/banner.jpg\" alt=\"imagen de portada\"></div>\n");
      out.write("        <footer>\n");
      out.write("            <div class=\"je-container container-footer\">\n");
      out.write("                <div class=\"je-item\">\n");
      out.write("                    <div class=\"footer-location\">\n");
      out.write("                        <h2 class=\"t4\">Jerson Ramírez Ortiz</h2>\n");
      out.write("                        <p class=\"location-conuntry\"> <span class=\"icon-contry\"><i class=\"fas fa-globe-europe\"></i>Huacho - Perú</span></p>\n");
      out.write("                        <p class=\"location-university\">Unjfsc  </p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"je-item\">\n");
      out.write("                    <div class=\"footer-logo\"><svg class=\"svg-footer\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" preserveAspectRatio=\"xMidYMid meet\" viewBox=\"478 126 799 370\"><g data-item-type=\"shape\" data-item=\"Shape\" data-logo-item=\"\" id=\"logo__item--shape_1\" class=\"logo__item logo__item--active\">\n");
      out.write("                        <g class=\"logo__item__inner\" transform=\"translate(421.95000076293945 106.5708999633789) scale(4.198380632663179 4.198380632663179) rotate(0 0 0)\">\n");
      out.write("                        <g>\n");
      out.write("                        <g>\n");
      out.write("                        <g>\n");
      out.write("                        <g>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M62.8,88.1c-1.8-0.6-3.6-0.1-3.9,1c-0.3,1.1,0.8,2.5,2.7,3c1.8,0.6,5-0.7,5-0.7S64.6,88.6,62.8,88.1z\" data-part-id=\"logo__item--shape_1__0\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M59.1,83.2c-1,1.2-1.2,2.7-0.4,3.3c0.8,0.6,2.2,0.1,3.2-1.1c1-1.2,1-4,1-4S60.1,82,59.1,83.2z\" data-part-id=\"logo__item--shape_1__1\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M64,81.4c-0.6,1.4-0.4,2.9,0.4,3.3c0.9,0.4,2.1-0.4,2.8-1.9c0.6-1.4-0.1-4.2-0.1-4.2S64.7,79.9,64,81.4z\" data-part-id=\"logo__item--shape_1__2\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M69.3,78.3c-0.6,1.5-0.3,2.9,0.6,3.3c0.9,0.4,2.1-0.6,2.7-2c0.6-1.5-0.3-4.1-0.3-4.1S69.9,76.9,69.3,78.3z\" data-part-id=\"logo__item--shape_1__3\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M74,74.8c-0.3,1.4,0.2,2.6,1.1,2.7c0.8,0.2,1.7-0.8,2-2.2c0.3-1.4-0.9-3.5-0.9-3.5S74.3,73.4,74,74.8z\" data-part-id=\"logo__item--shape_1__4\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M77.5,70.9c-0.1,1.3,0.4,2.3,1.2,2.4c0.8,0.1,1.5-0.9,1.6-2.1c0.1-1.3-1.1-3.2-1.1-3.2S77.6,69.6,77.5,70.9z\" data-part-id=\"logo__item--shape_1__5\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M79.9,66.8c0.1,1.1,0.7,1.9,1.4,1.8c0.7-0.1,1.1-1,1-2.1c-0.1-1.1-1.5-2.4-1.5-2.4S79.8,65.7,79.9,66.8z\" data-part-id=\"logo__item--shape_1__6\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M81.4,62.5c0.1,1.1,0.7,1.9,1.4,1.8c0.7-0.1,1.1-1,1-2.1c-0.1-1.1-1.4-2.5-1.4-2.5S81.3,61.4,81.4,62.5z\" data-part-id=\"logo__item--shape_1__7\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M68.8,86.4c-1.9-0.2-3.5,0.5-3.7,1.7c-0.1,1.2,1.3,2.3,3.2,2.5c1.9,0.2,4.8-1.6,4.8-1.6S70.7,86.7,68.8,86.4z\" data-part-id=\"logo__item--shape_1__8\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M73.7,83.3c-1.9,0.2-3.3,1.3-3.2,2.4c0.1,1.2,1.7,2,3.6,1.8c1.9-0.2,4.3-2.5,4.3-2.5S75.6,83.1,73.7,83.3z\" data-part-id=\"logo__item--shape_1__9\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M78.1,79.5c-1.5,0.6-2.4,1.8-2.1,2.7c0.4,0.9,1.9,1.2,3.3,0.6c1.5-0.6,2.9-3.1,2.9-3.1S79.6,78.9,78.1,79.5z\" data-part-id=\"logo__item--shape_1__10\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M81.3,74.4c-1.4,0.9-2.1,2.3-1.5,3.2c0.5,0.9,2.1,0.9,3.5,0c1.4-0.9,2.4-3.7,2.4-3.7S82.7,73.6,81.3,74.4z\" data-part-id=\"logo__item--shape_1__11\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M84,69.5c-1.2,0.9-1.6,2.2-1.1,2.9c0.6,0.7,2,0.6,3.1-0.3c1.2-0.9,1.8-3.5,1.8-3.5S85.2,68.6,84,69.5z\" data-part-id=\"logo__item--shape_1__12\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M85.4,65c-0.9,0.8-1.2,1.9-0.7,2.5c0.5,0.6,1.6,0.3,2.5-0.5c0.9-0.8,1.2-3,1.2-3S86.3,64.2,85.4,65z\" data-part-id=\"logo__item--shape_1__13\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M85.3,61.2c-0.7,1-0.7,2.1-0.1,2.5c0.6,0.4,1.7,0,2.3-1c0.7-1,0.5-3.1,0.5-3.1S86,60.2,85.3,61.2z\" data-part-id=\"logo__item--shape_1__14\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M84.1,62.4c0.1,3.7-1.6,7.2-3.6,10.2c-2.1,3-4.7,5.7-7.5,8c-2.9,2.2-6.1,4-9.5,5.3c-3.4,1.3-7,2-10.7,1.8l0,0.1      c3.6,0.9,7.5,0.3,11.1-0.9c3.6-1.2,6.8-3.3,9.7-5.7c2.9-2.4,5.4-5.2,7.4-8.3c1-1.6,1.8-3.3,2.5-5c0.6-1.8,1-3.6,0.8-5.5H84.1z\" data-part-id=\"logo__item--shape_1__15\"></path>\n");
      out.write("                        </g>\n");
      out.write("                        </g>\n");
      out.write("                        <g>\n");
      out.write("                        <g>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M39.9,88.1c1.8-0.6,3.6-0.1,3.9,1c0.3,1.1-0.8,2.5-2.7,3c-1.8,0.6-5-0.7-5-0.7S38.1,88.6,39.9,88.1z\" data-part-id=\"logo__item--shape_1__16\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M43.6,83.2c1,1.2,1.2,2.7,0.4,3.3c-0.8,0.6-2.2,0.1-3.2-1.1c-1-1.2-1-4-1-4S42.6,82,43.6,83.2z\" data-part-id=\"logo__item--shape_1__17\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M38.6,81.4c0.6,1.4,0.5,2.9-0.4,3.3c-0.9,0.4-2.1-0.4-2.8-1.9c-0.6-1.4,0.1-4.2,0.1-4.2S38,79.9,38.6,81.4z\" data-part-id=\"logo__item--shape_1__18\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M33.4,78.3c0.6,1.5,0.3,2.9-0.6,3.3c-0.9,0.4-2.1-0.6-2.7-2c-0.6-1.5,0.3-4.1,0.3-4.1S32.8,76.9,33.4,78.3z\" data-part-id=\"logo__item--shape_1__19\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M28.6,74.8c0.3,1.4-0.2,2.6-1.1,2.7c-0.8,0.2-1.7-0.8-2-2.2c-0.3-1.4,0.9-3.5,0.9-3.5S28.4,73.4,28.6,74.8z\" data-part-id=\"logo__item--shape_1__20\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M25.2,70.9c0.1,1.3-0.4,2.3-1.2,2.4c-0.8,0.1-1.5-0.9-1.6-2.1c-0.1-1.3,1.1-3.2,1.1-3.2S25.1,69.6,25.2,70.9z\" data-part-id=\"logo__item--shape_1__21\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M22.7,66.8c-0.1,1.1-0.7,1.9-1.4,1.8c-0.7-0.1-1.1-1-1-2.1c0.1-1.1,1.5-2.4,1.5-2.4S22.9,65.7,22.7,66.8z\" data-part-id=\"logo__item--shape_1__22\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M21.3,62.5c-0.1,1.1-0.7,1.9-1.4,1.8c-0.7-0.1-1.1-1-1-2.1c0.1-1.1,1.4-2.5,1.4-2.5S21.4,61.4,21.3,62.5z\" data-part-id=\"logo__item--shape_1__23\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M33.8,86.4c1.9-0.2,3.5,0.5,3.6,1.7c0.1,1.2-1.3,2.3-3.1,2.5c-1.9,0.2-4.8-1.6-4.8-1.6S31.9,86.7,33.8,86.4z\" data-part-id=\"logo__item--shape_1__24\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M29,83.3c1.9,0.2,3.3,1.3,3.2,2.4c-0.1,1.2-1.7,2-3.6,1.8c-1.9-0.2-4.3-2.5-4.3-2.5S27.1,83.1,29,83.3z\" data-part-id=\"logo__item--shape_1__25\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M24.6,79.5c1.5,0.6,2.4,1.8,2.1,2.7c-0.4,0.9-1.9,1.2-3.3,0.6c-1.5-0.6-2.9-3.1-2.9-3.1S23.1,78.9,24.6,79.5z\" data-part-id=\"logo__item--shape_1__26\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M21.3,74.4c1.4,0.9,2.1,2.3,1.5,3.2c-0.5,0.9-2.1,0.9-3.5,0C18,76.7,17,73.9,17,73.9S20,73.6,21.3,74.4z\" data-part-id=\"logo__item--shape_1__27\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M18.6,69.5c1.2,0.9,1.6,2.2,1.1,2.9c-0.6,0.7-2,0.6-3.1-0.3c-1.2-0.9-1.8-3.5-1.8-3.5S17.5,68.6,18.6,69.5z\" data-part-id=\"logo__item--shape_1__28\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M17.3,65c0.9,0.8,1.2,1.9,0.7,2.5c-0.5,0.6-1.6,0.3-2.5-0.5c-0.9-0.8-1.2-3-1.2-3S16.4,64.2,17.3,65z\" data-part-id=\"logo__item--shape_1__29\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M17.4,61.2c0.7,1,0.7,2.1,0.1,2.5c-0.6,0.4-1.7,0-2.3-1c-0.7-1-0.5-3.1-0.5-3.1S16.7,60.2,17.4,61.2z\" data-part-id=\"logo__item--shape_1__30\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M18.5,62.4c-0.1,3.7,1.6,7.2,3.6,10.2c2.1,3,4.7,5.7,7.5,8c2.9,2.2,6.1,4,9.5,5.3c3.4,1.3,7,2,10.7,1.8l0,0.1      c-3.7,0.9-7.6,0.3-11.1-0.9c-3.6-1.2-6.8-3.3-9.7-5.7c-2.9-2.4-5.4-5.2-7.4-8.3c-1-1.6-1.8-3.3-2.5-5c-0.6-1.8-1-3.6-0.8-5.5      H18.5z\" data-part-id=\"logo__item--shape_1__31\"></path>\n");
      out.write("                        </g>\n");
      out.write("                        </g>\n");
      out.write("                        </g>\n");
      out.write("                        <g>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M50.9,77.2c-33-19-28.8-54.8-28.8-55.2L22,21.7L51,9.1V5.4L18.7,19.3c0,0-4.7,40.2,32.3,61.8v-3.7L50.9,77.2z\" data-part-id=\"logo__item--shape_1__32\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M23.1,22.6C22.7,26.3,21,58.3,51,75.9V10.5L23.1,22.6z\" data-part-id=\"logo__item--shape_1__33\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M78.9,22.6L51,10.5v65.4C82,58.3,79.3,26.3,78.9,22.6z\" data-part-id=\"logo__item--shape_1__34\"></path>\n");
      out.write("                        <path fill=\"#00BFF0\" d=\"M83.3,19.3L51,5.4v3.7l29.3,12.6l0.1,0.4c0,0.4,4,36.2-29,55.2L51,77.4v3.7C88,59.6,83.3,19.3,83.3,19.3z\" data-part-id=\"logo__item--shape_1__35\"></path>\n");
      out.write("                        </g>\n");
      out.write("                        </g>\n");
      out.write("                        </g>\n");
      out.write("                        </g><g data-item-type=\"text\" data-item=\"Business\" id=\"logo__item--business\" class=\"logo__item\">\n");
      out.write("                        <g class=\"logo__item__inner\" transform=\"translate(812.8515625 408.3957167870051) scale(9.944122575173827 9.944122575173827) rotate(0 0 0)\">\n");
      out.write("                        <text data-part-id=\"logo__item--business\" dy=\"0\" dominant-baseline=\"auto\" alignment-baseline=\"auto\" font-family=\"Roboto Slab\" font-size=\"32px\" fill=\"#006ba8\" letter-spacing=\"0\" font-weight=\"normal\" font-style=\"normal\" data-font-family=\"Roboto Slab\" data-font-weight=\"normal\" data-font-style=\"normal\" data-ttf-url=\"/builder_assets/fontsttf/font-montserrat-bold-normal.ttf\" data-font-filename=\"roboto-slab-normal-normal\">I</text>\n");
      out.write("                        </g>\n");
      out.write("                        </g><g data-item-type=\"text\" id=\"logo__item--tagline_1\" data-item=\"Tagline\" class=\"logo__item\">\n");
      out.write("                        <g class=\"logo__item__inner\" transform=\"translate(550 332) scale(2.287397343362241 2.287397343362241) rotate(0 0 0)\">\n");
      out.write("                        <text dy=\"0\" dominant-baseline=\"auto\" alignment-baseline=\"auto\" font-family=\"Roboto Slab\" font-size=\"18px\" fill=\"#fff\" letter-spacing=\"1\" data-font-family=\"Roboto Slab\" data-font-weight=\"bold\" data-font-style=\"normal\" font-weight=\"bold\" font-style=\"normal\" data-ttf-url=\"/builder_assets/fontsttf/font-open-sans-normal-normal.ttf\" data-part-id=\"logo__item--tagline_1__0\" data-font-filename=\"roboto-slab-bold-normal\">UNJFSC</text>\n");
      out.write("                        </g>\n");
      out.write("                        </g><g data-item-type=\"text\" id=\"logo__item--tagline_2\" data-item=\"Tagline\" class=\"logo__item\">\n");
      out.write("                        <g class=\"logo__item__inner\" transform=\"translate(910 366) scale(10.346796974153193 10.346796974153193) rotate(0 0 0)\">\n");
      out.write("                        <text dy=\"0\" dominant-baseline=\"auto\" alignment-baseline=\"auto\" font-family=\"Roboto Slab\" font-size=\"18px\" fill=\"#757575\" letter-spacing=\"1\" data-font-family=\"Roboto Slab\" data-font-weight=\"normal\" data-font-style=\"normal\" font-weight=\"normal\" font-style=\"normal\" data-ttf-url=\"/builder_assets/fontsttf/font-open-sans-normal-normal.ttf\" data-part-id=\"logo__item--tagline_2__0\" data-font-filename=\"roboto-slab-normal-normal\">TEC</text>\n");
      out.write("                        </g>\n");
      out.write("                        </g><g data-item-type=\"text\" id=\"logo__item--tagline_3\" data-item=\"Tagline\" class=\"logo__item\">\n");
      out.write("                        <g class=\"logo__item__inner\" transform=\"translate(586 257) scale(2.3781111642065236 2.3781111642065236) rotate(0 0 0)\">\n");
      out.write("                        <text dy=\"0\" dominant-baseline=\"auto\" alignment-baseline=\"auto\" font-family=\"Roboto Slab\" font-size=\"18px\" fill=\"#fff\" letter-spacing=\"1\" data-font-family=\"Roboto Slab\" data-font-weight=\"bold\" data-font-style=\"normal\" font-weight=\"bold\" font-style=\"normal\" data-ttf-url=\"/builder_assets/fontsttf/font-open-sans-normal-normal.ttf\" data-part-id=\"logo__item--tagline_3__0\" data-font-filename=\"roboto-slab-bold-normal\">FIISI</text>\n");
      out.write("                        </g>\n");
      out.write("                        </g></svg>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"je-item\">\n");
      out.write("                    <div class=\"footer-contact\">\n");
      out.write("                        <h2 class=\"t4\">Contacto</h2>\n");
      out.write("                        <ul class=\"list-contact\">\n");
      out.write("                            <li class=\"contact-item\"><a href=\"\"><i class=\"fab fa-facebook-f\"></i></a></li>\n");
      out.write("                            <li class=\"contact-item\"><a href=\"\"><i class=\"fab fa-twitter\"></i></a></li>\n");
      out.write("                            <li class=\"contact-item\"><a href=\"\"><i class=\"fab fa-instagram\"></i></a></li>\n");
      out.write("                            <li class=\"contact-item\"><a href=\"\"><i class=\"fab fa-youtube\"> </i></a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("        <script>toogleButton('icon', 'navigation', 'active')</script>\n");
      out.write("        <script src=\"js/scripts-min.js\"></script>\n");
      out.write("        <script src=\"https://kit.fontawesome.com/56e0c4d4ed.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
