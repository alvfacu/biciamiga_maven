package Servlets;

import Entidades.Bicicletas;
import Entidades.DetallesMantenimiento;
import Entidades.Mantenimientos;
import Entidades.TiposMantenimiento;
import Negocio.ControladorBicicletas;
import Negocio.ControladorMantenimientos;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AltaMantenimiento", urlPatterns = {"/AltaMantenimiento"})
public class AltaMantenimiento extends HttpServlet {

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
      String idBici = request.getParameter("idBici");
      String obs = request.getParameter("obs");
      Bicicletas b = new ControladorBicicletas().getBicicleta(Integer.valueOf(idBici));

      double kmi = Double.valueOf(request.getParameter("km_ingreso"));
      String[] fechai = request.getParameter("fec_ingreso").split("\\/");
      String[] horai = request.getParameter("hr_ingreso").split("\\:");
      int hora = Integer.valueOf(horai[0]);
      int min = Integer.valueOf(horai[1]);
      int dia = Integer.valueOf(fechai[0]);
      int mes = Integer.valueOf(fechai[1]);
      int anio = Integer.valueOf(fechai[2]);
      Calendar fecha = Calendar.getInstance();
      fecha.set(anio, (mes - 1), dia, hora, min, 0);

      Mantenimientos m = new Mantenimientos(b, fecha.getTime(), null, obs, kmi, 0);
      new ControladorMantenimientos().altaMantenimiento(m);
      String[] values = request.getParameterValues("checkbox");
      for (String val : values) {
        TiposMantenimiento mant = new ControladorMantenimientos().getTipoMantenimiento(Integer.valueOf(val));
        DetallesMantenimiento det = new DetallesMantenimiento(m, mant, false);
        new ControladorMantenimientos().altaDetalle(det);
      }

      new ControladorBicicletas().habilitarBicicleta(false, b);

      response.sendRedirect("admmant.jsp");
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
