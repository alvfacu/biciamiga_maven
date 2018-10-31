package Servlets;

import Entidades.Modelos;
import Negocio.ControladorBicicletas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PreReserva", urlPatterns = {"/PreReserva"})
public class PreReserva extends HttpServlet {

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
    try{
      HttpSession session = request.getSession(true);
      String[] fechaReserva = request.getParameter("fechaReserva").split("-");
      int idModelo = Integer.valueOf(request.getParameter("idModeloReserva"));
      Modelos modReserva = new ControladorBicicletas().getModelo(idModelo);
      boolean completo = Boolean.valueOf(request.getParameter("completoReserva"));
      String importe = request.getParameter("importeReserva");
      
      if(!completo)
      {
        String desdeReserva = request.getParameter("hrDesdeReserva");
        String hastaReserva = request.getParameter("hrHastaReserva");
        session.setAttribute("desdeReserva", desdeReserva);
        session.setAttribute("hastaReserva", hastaReserva);
      }
      
      session.setAttribute("fechaReserva", fechaReserva[2]+"/"+fechaReserva[1]+"/"+fechaReserva[0]);
      session.setAttribute("modeloReserva", modReserva);
      session.setAttribute("completoReserva", completo);
      session.setAttribute("importeReserva", importe);
      request.getRequestDispatcher("prereserva.jsp").forward(request, response);
    }catch(IOException ex){ 
      response.sendRedirect("error.jsp");
    }catch(NumberFormatException ex){ 
      response.sendRedirect("error.jsp");
    }catch(ServletException ex){ 
      response.sendRedirect("error.jsp");
    }catch(Exception ex){ 
      response.sendRedirect("error.jsp");
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
