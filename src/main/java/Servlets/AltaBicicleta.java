package Servlets;

import Entidades.Bicicletas;
import Entidades.Modelos;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AltaBicicleta", urlPatterns = {"/AltaBicicleta"})
public class AltaBicicleta extends HttpServlet {

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
      int idmodelo = Integer.parseInt(request.getParameter("modelobici"));
      Modelos m = new ControladorBicicletas().getModelo(idmodelo);
      String patente = request.getParameter("patente").toUpperCase().trim();

      boolean disp = false;
      if (Boolean.valueOf(request.getParameter("disponible"))) {
        disp = true;
      }

      double kmMant = Double.valueOf(request.getParameter("kmMantenimiento"));
      double kmViaje = Double.valueOf(request.getParameter("kmViajados"));
      String descripcion = request.getParameter("patente").trim();
      Bicicletas b = new Bicicletas(patente, descripcion, m, disp, kmViaje, kmMant);
      new ControladorBicicletas().altaBicicleta(b);
      response.sendRedirect("admbici.jsp");
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
