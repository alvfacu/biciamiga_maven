package Servlets;

import Entidades.Usuarios;
import Negocio.ControladorUsuarios;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ResetContrasenia", urlPatterns = {"/ResetContrasenia"})
public class ResetContrasenia extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    try {
      int id = Integer.valueOf(request.getParameter("id"));
      Usuarios u = new ControladorUsuarios().getUsuario(id);
      String porDefecto = "12345";
      if (u != null) {
        try {
          u.setContrasenia(new Util.Seguridad().md5(porDefecto));
          new ControladorUsuarios().modificarUsuario(u);
          response.setContentType("text/plain");
          response.getWriter().write("0");
        } catch (NoSuchAlgorithmException ex) {
          response.setContentType("text/plain");
          response.getWriter().write("1");
        }
      } else {
        response.setContentType("text/plain");
        response.getWriter().write("1");
      }
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
