package Servlets;

import Entidades.Mantenimientos;
import Negocio.ControladorBicicletas;
import Negocio.ControladorMantenimientos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EliminarMantenimiento", urlPatterns = {"/EliminarMantenimiento"})
public class EliminarMantenimiento extends HttpServlet {

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
      String idMant = request.getParameter("idMant");
      Mantenimientos m = new ControladorMantenimientos().getMantenimiento(Integer.valueOf(idMant));
      new ControladorBicicletas().habilitarBicicleta(true, m.getBici());
      new ControladorMantenimientos().bajaMantenimiento(m);
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
