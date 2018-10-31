package Entidades;

import java.util.Date;

public class Reservas {
  
  private Usuarios cliente;
  private Bicicletas bici;
  private int id;
  private Date fechaInterna, fechaInicioP, fechaFinP, fechaInicioR, fechaFinR;  
  private double importe;
  private String observacion;
  private EstadosReserva estado;
  private double kmRecorridos;

  public Usuarios getCliente() {
    return cliente;
  }

  public void setCliente(Usuarios cliente) {
    this.cliente = cliente;
  }

  public Bicicletas getBici() {
    return bici;
  }

  public void setBici(Bicicletas bici) {
    this.bici = bici;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getFechaInterna() {
    return fechaInterna;
  }

  public void setFechaInterna(Date fechaInterna) {
    this.fechaInterna = fechaInterna;
  }

  public Date getFechaInicioP() {
    return fechaInicioP;
  }

  public void setFechaInicioP(Date fechaInicioP) {
    this.fechaInicioP = fechaInicioP;
  }

  public Date getFechaFinP() {
    return fechaFinP;
  }

  public void setFechaFinP(Date fechaFinP) {
    this.fechaFinP = fechaFinP;
  }
  
  public Date getFechaInicioR() {
    return fechaInicioR;
  }

  public void setFechaInicioR(Date fechaInicioR) {
    this.fechaInicioR = fechaInicioR;
  }
  
  public Date getFechaFinR() {
    return fechaFinR;
  }

  public void setFechaFinR(Date fechaFinR) {
    this.fechaFinR = fechaFinR;
  }

  public double getImporte() {
    return importe;
  }

  public void setImporte(double importe) {
    this.importe = importe;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public EstadosReserva getEstado() {
    return estado;
  }

  public void setEstado(EstadosReserva estado) {
    this.estado = estado;
  }

  public double getKmRecorridos() {
    return kmRecorridos;
  }

  public void setKmRecorridos(double kmRecorridos) {
    this.kmRecorridos = kmRecorridos;
  }
  
  public Reservas(Usuarios cliente, Bicicletas bici, Date fechaInterna, Date fechaInicioP, Date fechaFinP, double importe, EstadosReserva estado) {
    this.cliente = cliente;
    this.bici = bici;
    this.fechaInterna = fechaInterna;
    this.fechaInicioP = fechaInicioP;
    this.fechaFinP = fechaFinP;
    this.importe = importe;
    this.estado = estado;
  }
  
  public Reservas() {    
  }
    
}
