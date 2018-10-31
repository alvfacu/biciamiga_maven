/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Entidades.EstadosReserva;
import Entidades.Reservas;
import Entidades.Usuarios;
import Negocio.ControladorBicicletas;
import Negocio.ControladorMantenimientos;
import Negocio.ControladorReservas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Facundo
 */
@WebServlet(name = "FallasReserva", urlPatterns = {"/FallasReserva"})
public class FallasReserva extends HttpServlet {

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
      if (usrActual.isAdm() && reservaActual.getEstado()==EstadosReserva.ENCURSO) {        
        String[] fechae = request.getParameter("fec_fin").split("\\/");
        String[] horae = request.getParameter("hr_fin").split("\\:");
        int hora = Integer.valueOf(horae[0]);
        int min = Integer.valueOf(horae[1]);
        int dia = Integer.valueOf(fechae[2]);
        int mes = Integer.valueOf(fechae[1]);
        int anio = Integer.valueOf(fechae[0]);
        Calendar fecha = Calendar.getInstance();
        fecha.set(anio, (mes - 1), dia, hora, min, 0);
        reservaActual.setFechaFinR(fecha.getTime());
        
        double kmfinales = Double.valueOf(request.getParameter("km_fin"));
        double dif = kmfinales-reservaActual.getBici().getKmDsdMantenimiento();
        reservaActual.setKmRecorridos(dif);
        
        if(request.getParameter("obs").isEmpty())
          reservaActual.setObservacion("RESERVA FINALIZADA CON FALLAS");
        else
          reservaActual.setObservacion(request.getParameter("obs"));
        
        new ControladorReservas().fallasReserva(reservaActual);
        
        reservaActual.getBici().sumarKmRecorrisoa(dif);
        new ControladorBicicletas().modificarBicicleta(reservaActual.getBici());
        
        int idBici = reservaActual.getBici().getId();
        reservaActual = null;
        response.sendRedirect("nuevoMantenimiento.jsp?idBici="+idBici);
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
