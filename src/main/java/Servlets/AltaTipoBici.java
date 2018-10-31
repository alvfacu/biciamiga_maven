package Servlets;

import Entidades.TiposBicicleta;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AltaTipoBici", urlPatterns = {"/AltaTipoBici"})
public class AltaTipoBici extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    {
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    try {
      String nombre = request.getParameter("nombretb");
      String descripcion = request.getParameter("descriptb");
      String url = request.getParameter("urlPortada");
      TiposBicicleta tb = new TiposBicicleta(nombre, descripcion, url);

      new ControladorBicicletas().altaTipoBicicleta(tb);
      response.sendRedirect("admbici.jsp");
    } catch (IOException ex) {
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
