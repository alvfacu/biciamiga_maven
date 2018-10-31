package Servlets;

import Entidades.EstadosReserva;
import Entidades.Reservas;
import Entidades.Usuarios;
import Negocio.ControladorReservas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EliminarReserva", urlPatterns = {"/EliminarReserva"})
public class EliminarReserva extends HttpServlet {

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
      Usuarios usrActual = (Usuarios)session.getAttribute("Usuario");
      int idReserva = Integer.valueOf(request.getParameter("id"));
      Reservas reservaActual = new ControladorReservas().getReserva(idReserva);
      
      if (usrActual.isAdm() && reservaActual.getEstado()==EstadosReserva.PENDIENTE) {
        new ControladorReservas().eliminarReserva(reservaActual.getId());
        reservaActual = null;
        response.sendRedirect("admres.jsp");
      } else {
        response.sendRedirect("error.jsp");
      }
    } catch (NumberFormatException ex) {
      response.sendRedirect("error.jsp");
    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (Exception ex) {
      response.sendRedirect("error.jsp");
    }
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
