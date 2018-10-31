package Servlets;

import Entidades.Modelos;
import Entidades.Usuarios;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Reservar", urlPatterns = {"/Reservar"})
public class Reservar extends HttpServlet {

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
      ArrayList<Modelos> disponibles;
      Usuarios usr = (Usuarios) session.getAttribute("Usuario");
      if (usr != null && ((usr.isAdm()) || (!usr.isAdm() && !usr.isMecanico())) && usr.isHabilitado()) {
        Calendar fechaDsd = Calendar.getInstance();
        Calendar fechaHst = Calendar.getInstance();
        fechaDsd.set(Calendar.HOUR_OF_DAY, 9);
        fechaDsd.set(Calendar.MINUTE, 0);
        fechaDsd.set(Calendar.SECOND, 0);
        fechaDsd.set(Calendar.MILLISECOND, 0);
        fechaHst.set(Calendar.HOUR_OF_DAY, 21);
        fechaHst.set(Calendar.MINUTE, 0);
        fechaHst.set(Calendar.SECOND, 0);
        fechaHst.set(Calendar.MILLISECOND, 0);
        disponibles = new ControladorBicicletas().getModelosDisponibles(fechaDsd, fechaHst);

        request.setAttribute("bicicletas", disponibles);
        request.getRequestDispatcher("reservar.jsp").forward(request, response);
      } else if (usr == null) {
        response.sendRedirect("login.jsp");
      } else {
        response.sendRedirect("error.jsp");
      }
    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (ServletException ex) {
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
