package Servlets;

import Entidades.Usuarios;
import Negocio.ControladorUsuarios;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    try {
      HttpSession session = request.getSession(true);
      session.setMaxInactiveInterval(3600);
      String usuario = request.getParameter("user");
      String password = request.getParameter("pass");
      ControladorUsuarios cu = new ControladorUsuarios();
            
      Usuarios usr = cu.validarUsuario(usuario, password);
      if (usr == null) {
        session.setAttribute("Usuario", null);
        session.setAttribute("Msj", "Usuario o contraseña incorrecto/s");
        response.sendRedirect("login.jsp");
      } else {
        session.setAttribute("Usuario", usr);
        session.setAttribute("Msj", null);
        response.sendRedirect("index.jsp");
      }
    } catch (IOException ex) {      
      response.sendRedirect("error.jsp");
    } catch (NoSuchAlgorithmException ex) {      
      response.sendRedirect("error.jsp");
    } catch (Exception ex) {      
      response.sendRedirect("error.jsp");
    }

  }

}
