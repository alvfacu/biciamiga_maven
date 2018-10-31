/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Negocio.ControladorReservas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Facundo
 */
@WebServlet(name = "PuedeIniciarReserva", urlPatterns = {"/PuedeIniciarReserva"})
public class PuedeIniciarReserva extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
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
    String rdo = "0";
    try {
      if ("".equals(request.getParameter("idReserva"))){        
        rdo = "1";
      } else {
        int idR = Integer.valueOf(request.getParameter("idReserva"));
        if (!new ControladorReservas().puedeIniciar(idR)) {
          rdo = "1";
        }
        else if(!new ControladorReservas().estaDisponible(idR)){
          rdo = "2";
        }
      }
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (IOException ex) {
      rdo = "1";
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (NumberFormatException ex) {
      rdo = "1";
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
    } catch (Exception ex) {
      rdo = "1";
      response.setContentType("text/plain");
      response.getWriter().write(rdo);
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
