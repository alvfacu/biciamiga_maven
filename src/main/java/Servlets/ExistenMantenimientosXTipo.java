package Servlets;

import Negocio.ControladorMantenimientos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExistenMantenimientosXTipo", urlPatterns = {"/ExistenMantenimientosXTipo"})
public class ExistenMantenimientosXTipo extends HttpServlet {

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
    String rdo = "0";

    try {
      String id = request.getParameter("id").trim();
      int cant = new ControladorMantenimientos().existenMantenimientosActivosXTipo(id);

      if (cant > 0) {
        rdo = "1";
      }

      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (IOException ex) {
      response.setContentType("text/plain");
      response.getWriter().write("1");
    } catch (Exception ex) {
      response.setContentType("text/plain");
      response.getWriter().write("1");
    }

  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
