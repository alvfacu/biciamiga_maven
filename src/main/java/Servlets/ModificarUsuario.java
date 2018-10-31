package Servlets;

import Entidades.Usuarios;
import Negocio.ControladorUsuarios;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ModificarUsuario", urlPatterns = {"/ModificarUsuario"})
public class ModificarUsuario extends HttpServlet {

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
      int id = Integer.valueOf(request.getParameter("idusr"));
      Usuarios u = new ControladorUsuarios().getUsuario(id);
      u.setApynom(request.getParameter("apenom"));
      u.setDomicilio(request.getParameter("domicilio"));
      u.setDocumento(request.getParameter("documento"));
      u.setTelefono(request.getParameter("telefono"));
      u.setEmail(request.getParameter("email"));

      boolean admin = false;
      boolean meca = false;
      if (Boolean.valueOf(request.getParameter("admin"))) {
        admin = true;
        meca = true;
      } else if (Boolean.valueOf(request.getParameter("meca"))) {
        meca = true;
        admin = false;
      }

      boolean habilitado = false;
      if (Boolean.valueOf(request.getParameter("habilitado"))) {
        habilitado = true;
      }

      u.setAdm(admin);
      u.setMecanico(meca);
      u.setHabilitado(habilitado);
      new ControladorUsuarios().modificarUsuario(u);
      response.sendRedirect("admusr.jsp");
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
