package Servlets;

import Entidades.Bicicletas;
import Entidades.EstadosReserva;
import Entidades.Modelos;
import Entidades.Reservas;
import Entidades.Usuarios;
import Negocio.ControladorBicicletas;
import Negocio.ControladorReservas;
import Negocio.ControladorUsuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AltaReserva", urlPatterns = {"/AltaReserva"})
public class AltaReserva extends HttpServlet {

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
      if (usr != null && ((usr.isAdm()) || (!usr.isAdm() && !usr.isMecanico())) && usr.isHabilitado()) {
        Modelos modeloReserva = (Modelos) session.getAttribute("modeloReserva");
        ArrayList<Bicicletas> bicisReserva = new ControladorBicicletas().getBicicletaParaReserva(modeloReserva.getId());

        if (bicisReserva.size() > 0) {
          String[] fechaR = session.getAttribute("fechaReserva").toString().split("/");
          boolean completo = Boolean.valueOf(session.getAttribute("completoReserva").toString());
          double importe = Double.valueOf(session.getAttribute("importeReserva").toString());

          int hrDsd = 9;
          int hrHst = 21;

          if (!completo) {
            hrDsd = Integer.valueOf(session.getAttribute("desdeReserva").toString());
            hrHst = Integer.valueOf(session.getAttribute("hastaReserva").toString());
          } else {
            session.setAttribute("desdeReserva", "09");
          }

          if (usr.isAdm()) {
            int idUsr = Integer.valueOf(request.getParameter("usuarios"));
            usr = new ControladorUsuarios().getUsuario(idUsr);
          }

          Calendar fechaActual = Calendar.getInstance();
          Calendar fechaReservaInicio = Calendar.getInstance();
          Calendar fechaReservaFin = Calendar.getInstance();
          fechaReservaInicio.set(Integer.valueOf(fechaR[2]), Integer.valueOf(fechaR[1]) - 1, Integer.valueOf(fechaR[0]), hrDsd, 0, 0);
          fechaReservaFin.set(Integer.valueOf(fechaR[2]), Integer.valueOf(fechaR[1]) - 1, Integer.valueOf(fechaR[0]), hrHst, 0, 0);

          Bicicletas biciReserva = null;

          for (Bicicletas b : bicisReserva) {
            if (new ControladorBicicletas().estaDisponibleParaReserva(b, fechaReservaInicio, fechaReservaFin)) {
              biciReserva = b;
              break;
            }
          }

          if (biciReserva != null) {
            Reservas nuevaReserva = new Reservas(usr, biciReserva, fechaActual.getTime(), fechaReservaInicio.getTime(), fechaReservaFin.getTime(), importe, EstadosReserva.PENDIENTE);
            new ControladorReservas().altaReserva(nuevaReserva);
            session.setAttribute("hastaReserva", null);
            session.setAttribute("modeloReserva", null);
            session.setAttribute("completoReserva", null);
            session.setAttribute("importeReserva", null);
            session.setAttribute("numeroReserva", "R" + String.format("%5s", nuevaReserva.getId()).replace(' ', '0'));
            request.getRequestDispatcher("reserva_exitosa.jsp").forward(request, response);
          } else {
            response.sendRedirect("error.jsp");
          }
        } else {
          response.sendRedirect("error.jsp");
        }
      }
    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (NumberFormatException ex) {
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
