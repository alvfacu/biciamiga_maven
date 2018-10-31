package Negocio;

import Datos.CatalogoReservas;
import Entidades.EstadosReserva;
import Entidades.Reservas;
import Entidades.Usuarios;
import java.util.ArrayList;
import java.util.Date;

public class ControladorReservas {
  
  CatalogoReservas cr;
  
  public ControladorReservas() {
    this.cr = new CatalogoReservas();
  }

  public ArrayList<Reservas> getReservas() {
    return cr.getReservas();
  }
  
  public ArrayList<Reservas> getReservasPendientes() {
    return cr.getReservasNoFinalizadas(EstadosReserva.PENDIENTE.getId());
  }
  
  public ArrayList<Reservas> getReservasEnCurso() {
    return cr.getReservasNoFinalizadas(EstadosReserva.ENCURSO.getId());
  }
  
  public ArrayList<Reservas> getReservasFinalizados() {
    return cr.getReservasFinalizadas2();
  }
 
  public ArrayList<Reservas> getReservasPendientesXUsr(Usuarios usr){
    return cr.getReservasPendientesXUsr2(usr.getId());
  }
  
  public ArrayList<Reservas> getReservasFinalizadasXUsr(Usuarios usr){
    return cr.getReservasFinalizadasXUsr2(usr.getId());
  }
  
  public Reservas getReserva(int id) {
    return cr.getReserva(id);
  }

  public void altaReserva(Reservas r) {
    cr.altaReserva(r);
  }

  public void bajaReserva(Reservas r){
    cr.bajaReserva(r);
  }

  public void modificarReserva(Reservas r){
    cr.modificarReserva(r);
  }

  public void cancelarReserva(int idReserva) {
    cr.cancelarReserva(idReserva);
  }

  public boolean puedeIniciar(int idR) {    
    Reservas reservaActual = cr.getReserva(idR);
    Date actual = new Date();
    
   return (reservaActual.getEstado() == EstadosReserva.PENDIENTE && reservaActual.getFechaInicioP().before(actual) && actual.before(reservaActual.getFechaFinP()));
  }

  public void iniciarReserva(int id) {
    cr.iniciarReserva(id);
  }

  public void eliminarReserva(int id) {
    cr.eliminarReserva(id);
  }

  public void finalizarReserva(Reservas reservaActual) {
    cr.finalizarReserva(reservaActual);
  }

  public void fallasReserva(Reservas reservaActual) {
    cr.fallasReserva(reservaActual);
  }

  public boolean estaDisponible(int idR) {
    Reservas reservaActual = cr.getReserva(idR);
    
    return (reservaActual.getBici().isDisponible());
            
  }
  
}
