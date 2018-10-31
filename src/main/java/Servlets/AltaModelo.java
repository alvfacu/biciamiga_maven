package Servlets;

import Entidades.Modelos;
import Entidades.TiposBicicleta;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AltaModelo", urlPatterns = {"/AltaModelo"})
public class AltaModelo extends HttpServlet {

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
      int idTipo = Integer.valueOf(request.getParameter("clr"));
      TiposBicicleta tb = new ControladorBicicletas().getTipo(idTipo);

      String nombre = request.getParameter("nombrem");
      String descrip = request.getParameter("descripm");
      double preciohr = Double.valueOf(request.getParameter("precioHr"));
      double preciodia = Double.valueOf(request.getParameter("precioDia"));
      String url1 = request.getParameter("url1");
      String url2 = request.getParameter("url2");
      String url3 = request.getParameter("url3");

      Modelos m = new Modelos(nombre, descrip, preciohr, preciodia, url1, url2, url3, tb);
      new ControladorBicicletas().altaModelo(m);
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
