package Servlets;

import Entidades.EstadosReserva;
import Entidades.Reservas;
import Negocio.ControladorReservas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Facundo
 */
@WebServlet(name = "CancelarReserva", urlPatterns = {"/CancelarReserva"})
public class CancelarReserva extends HttpServlet {

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
      int idReserva = Integer.valueOf(request.getParameter("id"));
      Reservas reservaActual = new ControladorReservas().getReserva(idReserva);
      int idUsr = Integer.valueOf(request.getParameter("idUsr"));
      if (reservaActual.getCliente().getId()==idUsr && reservaActual.getEstado()==EstadosReserva.PENDIENTE) {
        new ControladorReservas().cancelarReserva(reservaActual.getId());
        reservaActual = null;
        response.sendRedirect("mis_reservas.jsp");
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

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
