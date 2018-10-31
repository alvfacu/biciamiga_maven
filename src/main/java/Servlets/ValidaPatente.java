package Servlets;

import Negocio.ControladorBicicletas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ValidaPatente", urlPatterns = {"/ValidaPatente"})
public class ValidaPatente extends HttpServlet {

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
      String patente = request.getParameter("patente").toUpperCase().trim();

      if ("".equals(patente)) {
        rdo = "1";
      } else {
        int cant = new ControladorBicicletas().existePatente(patente);

        if (cant > 0) {
          rdo = "1";
        }
      }
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (IOException ex) {
      response.setContentType("text/plain");
      response.getWriter().write("1");
    } catch (Exception ex) {
      response.setContentType("text/plain");
      response.getWriter().write("!");
    }

  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
