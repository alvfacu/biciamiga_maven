package Servlets;

import Entidades.Bicicletas;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DameKmBicicleta", urlPatterns = {"/DameKmBicicleta"})
public class DameKmBicicleta extends HttpServlet {

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
      String idBici = request.getParameter("idbici");
      Bicicletas b = new ControladorBicicletas().getBicicleta(Integer.valueOf(idBici));
      String rdo = String.format("%.2f", b.getKmEnViaje());
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (NumberFormatException ex) {
      response.setContentType("text/plain");
      response.getWriter().write("0");
    } catch(Exception ex){
      response.setContentType("text/plain");
      response.getWriter().write("0");
    }

  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
