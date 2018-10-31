package Servlets;

import Entidades.TiposMantenimiento;
import Negocio.ControladorMantenimientos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AltaTipoMantenimiento", urlPatterns = {"/AltaTipoMantenimiento"})
public class AltaTipoMantenimiento extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    try {
      String nombre = request.getParameter("nombretm");
      String descripcion = request.getParameter("descriptm");

      Boolean oblig = false;
      if (Boolean.valueOf(request.getParameter("obligatorio"))) {
        oblig = true;
      }

      double km = 0;
      if (request.getParameter("km") != null) {
        km = Double.valueOf(request.getParameter("km"));
      }

      TiposMantenimiento tm = new TiposMantenimiento(km, oblig, descripcion, nombre);

      new ControladorMantenimientos().altaTipoMantenimiento(tm);
      response.sendRedirect("admmant.jsp");
    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (NumberFormatException ex) {
      response.sendRedirect("error.jsp");
    } catch (Exception ex) {
      response.sendRedirect("error.jsp");
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
