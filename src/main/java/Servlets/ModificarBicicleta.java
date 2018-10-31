package Servlets;

import Entidades.Bicicletas;
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

@WebServlet(name = "ModificarBicicleta", urlPatterns = {"/ModificarBicicleta"})
public class ModificarBicicleta extends HttpServlet {

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
      int id = Integer.valueOf(request.getParameter("idb"));
      Bicicletas biciAct = new ControladorBicicletas().getBicicleta(id);
      
      String descripcion = request.getParameter("patente").trim();
      
      biciAct.setDescripcion(descripcion);
      new ControladorBicicletas().modificarBicicleta(biciAct);

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
