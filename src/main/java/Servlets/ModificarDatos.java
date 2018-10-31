package Servlets;

import Entidades.Usuarios;
import Negocio.ControladorUsuarios;
import Util.Seguridad;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ModificarDatos", urlPatterns = {"/ModificarDatos"})
public class ModificarDatos extends HttpServlet {

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
      HttpSession session = request.getSession(true);
      Usuarios usr = (Usuarios) session.getAttribute("Usuario");
      if (usr != null) {
        Usuarios u = new ControladorUsuarios().getUsuario(usr.getId());
        u.setApynom(request.getParameter("apenom"));
        u.setDomicilio(request.getParameter("domicilio"));
        u.setDocumento(request.getParameter("documento"));
        u.setTelefono(request.getParameter("telefono"));
        u.setEmail(request.getParameter("email"));

        if ((request.getParameter("pass")!=null && !request.getParameter("pass").isEmpty() )) {
          u.setContrasenia(new Seguridad().md5(request.getParameter("pass")));
        }

        new ControladorUsuarios().modificarUsuario(u);
        session.setAttribute("Usuario", u);
        session.setAttribute("msj", "Correcto");
        response.sendRedirect("mi_cuenta.jsp");
      } else {
        response.sendRedirect("error.jsp");
      }
    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (NoSuchAlgorithmException ex) {
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
