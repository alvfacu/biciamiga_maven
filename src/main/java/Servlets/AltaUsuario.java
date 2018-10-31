package Servlets;

import Entidades.Usuarios;
import Negocio.ControladorUsuarios;
import Util.Seguridad;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AltaUsuario", urlPatterns = {"/AltaUsuario"})
public class AltaUsuario extends HttpServlet {

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
      String usuario = request.getParameter("usuario");
      //RE VALIDO que el usuario no exista
      if(new ControladorUsuarios().existeUsuario(usuario)==0)
      {
        //RE VALIDO que las contraseñas sean iguales
        //String a = request.getParameter("pass");
        //a = request.getParameter("repass");
        
        if(request.getParameter("pass").compareTo(request.getParameter("repass")) == 0)
        {
          String apenom = request.getParameter("apenom");
          String domi = request.getParameter("domicilio");
          String documento = request.getParameter("documento");
          String clave = new Seguridad().md5(request.getParameter("pass"));
          String telefono = request.getParameter("telefono");
          String mail = request.getParameter("email");

          boolean admin = false;
          if (Boolean.valueOf(request.getParameter("admin"))) {
            admin = true;
          }

          boolean meca = false;
          if (Boolean.valueOf(request.getParameter("meca"))) {
            meca = true;
          }

          boolean habilitado = false;

          if (Boolean.valueOf(request.getParameter("habilitado"))) {
            habilitado = true;
          }

          Usuarios u = new Usuarios(apenom, usuario, clave, mail, domi, telefono, documento, admin, habilitado, meca);
          ControladorUsuarios cu = new ControladorUsuarios();
          cu.altaUsuario(u);

          HttpSession session = request.getSession(true);
          Usuarios usrActual = (Usuarios) session.getAttribute("Usuario");
          if (usrActual != null && usrActual.isAdm()) {
            response.sendRedirect("admusr.jsp");
          } else {
            response.sendRedirect("registro_exitoso.jsp");
          }
        }
        else{
          response.sendRedirect("error.jsp");
        }      
      }
      else{
        response.sendRedirect("error.jsp");
      } 
    } catch (NoSuchAlgorithmException ex) {
      response.sendRedirect("error.jsp");
    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (Exception ex) {
      response.sendRedirect("error.jsp");
    }
  }
}
