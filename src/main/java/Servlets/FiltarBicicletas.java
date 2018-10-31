package Servlets;

import Entidades.Modelos;
import Entidades.TiposBicicleta;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "reservar", urlPatterns = {"/reservar"})
public class FiltarBicicletas extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      int idTipo = Integer.valueOf(request.getParameter("tipos"));
      int idTipoVerif = idTipo;
      int idModelo = 0;
      if (idTipo > 0) {
        TiposBicicleta tipo = new ControladorBicicletas().getTipo(idTipo);
        if (tipo != null) {
          String model = request.getParameter("modelos");
          String[] modelo = model.split("-");
          if (modelo.length > 1) {
            idTipoVerif = Integer.valueOf(modelo[0]);
            tipo = new ControladorBicicletas().getTipo(idTipoVerif);
            if (tipo == null) {
              request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            idModelo = Integer.valueOf(modelo[1]);

            Modelos mod = new ControladorBicicletas().getModelo(idModelo);
            if (mod == null) {
              request.getRequestDispatcher("error.jsp").forward(request, response);
            }
          } else if (Integer.valueOf(modelo[0]) != 0) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
          }
        } else {
          request.getRequestDispatcher("error.jsp").forward(request, response);
        }
      }

      if (idTipo == idTipoVerif) {
        boolean completo = false;
        if (request.getParameter("completo")== null || request.getParameter("completo").contains("true") || request.getParameter("completo").contains("false")) {
          if (Boolean.valueOf(request.getParameter("completo"))) {
            completo = true;
          }
        } else {
          
          request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        int hrDsd = 9;
        int hrHst = 21;

        if (!completo && request.getParameter("hrdesde") != null && request.getParameter("hrhasta") != null) {
          int desde = Integer.valueOf(request.getParameter("hrdesde"));
          if (desde > 9) {
            hrDsd = desde;
          } else if (desde < 9) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
          }

          int hasta = Integer.valueOf(request.getParameter("hrhasta"));
          if (hasta < 21) {
            hrHst = hasta;
          } else if (hasta > 21) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
          }
        }

        Calendar fechaReserva = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();

        String[] fecha = request.getParameter("fecha").split("-");
        int anio = Integer.valueOf(fecha[0]);
        int mes = Integer.valueOf(fecha[1]);
        int dia = Integer.valueOf(fecha[2]);
        fechaReserva.set(anio, (mes - 1), dia);
        fechaReserva.set(Calendar.HOUR, 0);
        fechaReserva.set(Calendar.MINUTE, 0);
        fechaReserva.set(Calendar.SECOND, 0);
        fechaReserva.set(Calendar.MILLISECOND, 0);

        fechaActual.set(Calendar.HOUR, 0);
        fechaActual.set(Calendar.MINUTE, 0);
        fechaActual.set(Calendar.SECOND, 0);
        fechaActual.set(Calendar.MILLISECOND, 0);

        if (fechaReserva.before(fechaActual)) {
          request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        Calendar fechaDsd = Calendar.getInstance();
        Calendar fechaHst = Calendar.getInstance();
        fechaDsd.set(fechaReserva.get(Calendar.YEAR), fechaReserva.get(Calendar.MONTH), fechaReserva.get(Calendar.DAY_OF_MONTH), hrDsd, 0, 0);
        fechaHst.set(fechaReserva.get(Calendar.YEAR), fechaReserva.get(Calendar.MONTH), fechaReserva.get(Calendar.DAY_OF_MONTH), hrHst, 0, 0);

        ArrayList<Modelos> disponibles;
        if (idTipo > 0 && idModelo > 0) {
          disponibles = new ControladorBicicletas().getModelosDisponiblesXTipoXModelo(idTipo, idModelo, fechaDsd, fechaHst);
        } else if (idTipo > 0 && idModelo == 0) {
          disponibles = new ControladorBicicletas().getModelosDisponiblesXTipo(idTipo, fechaDsd, fechaHst);
        } else {
          disponibles = new ControladorBicicletas().getModelosDisponibles(fechaDsd, fechaHst);
        }

        request.setAttribute("bicicletas", disponibles);
        request.getRequestDispatcher("reservar.jsp").forward(request, response);
      } else {
        response.sendRedirect("error.jsp");
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
