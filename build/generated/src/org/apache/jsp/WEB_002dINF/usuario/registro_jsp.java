package org.apache.jsp.WEB_002dINF.usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class registro_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("    <head>\n");
      out.write("        <title>Home</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"initial-scale=1.0, width=device-width\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700|Open+Sans:700\">\n");
      out.write("        <link rel=\"shortcut icon\" href=\"img/svg/logo.svg\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/base/base.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/theme/colors.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/theme/typography.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/theme/fonts.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/layout/layout.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/components/buttons.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/pages/templateAbstract.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/components/formulario.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");

           if(request.getAttribute("mensaje")!=null)
           {
            out.println("<div>"+(String)request.getAttribute("mensaje")+"</div>");
        }
        
      out.write("\n");
      out.write("\n");
      out.write("    <main class=\"container-allPage\">\n");
      out.write("        <div class=\"allPage-contains\">\n");
      out.write("            <div class=\"allPage_header\">\n");
      out.write("                <h1 class=\"small text-right\">Registro</h1>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"allPage_main\"> \n");
      out.write("                <form action=\"registro\" method=\"post\" name=\"form-registro\">\n");
      out.write("                    <input type=\"hidden\" name=\"accion\" value=\"registar_usuario\">\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"name\">Nombre:</label>\n");
      out.write("                        <input type=\"text\" id=\"name\" name=\"name\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"last-name\">Apellidos:</label>\n");
      out.write("                        <input type=\"text\" id=\"last-name\" name=\"last-name\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"age\">Email:</label>\n");
      out.write("                        <input type=\"text\" id=\"age\" name=\"age\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"username\">Username:</label>\n");
      out.write("                        <input type=\"text\" id=\"username\" name=\"username\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"pass\">Password:</label>\n");
      out.write("                        <input type=\"password\" id=\"pass\" name=\"pass\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"pass-r\">Repite la password:</label>\n");
      out.write("                        <input type=\"password\" id=\"pass-r\" name=\"passR\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <input class=\"button center-button\" type=\"submit\" value=\"Registrarme\">\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"allPage_footer\"></div>\n");
      out.write("        </div>\n");
      out.write("    </main>\n");
      out.write("</body>\n");
      out.write("</html>");
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
