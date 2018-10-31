package Servlets;

import Negocio.ControladorBicicletas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DameImagenBici", urlPatterns = {"/DameImagenBici"})
public class DameImagenBici extends HttpServlet {

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
      String idModelo = request.getParameter("idModelo");
      String rdo = new ControladorBicicletas().dameUrl(idModelo);
      String tipo = new ControladorBicicletas().getModelo(Integer.valueOf(idModelo)).getTipo().getNombre();
      String caracterEspecial = "//////";
      rdo += caracterEspecial;
      rdo += tipo;
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (IOException ex) {
      response.setContentType("text/plain");
      response.getWriter().write("");
    } catch (NumberFormatException ex) {
      response.setContentType("text/plain");
      response.getWriter().write("");
    } catch (Exception ex) {
      response.setContentType("text/plain");
      response.getWriter().write("");
    }

  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
