package Entidades;

public class Bicicletas {
  
  private int id;
  private String patente;  
  private String descripcion;
  private Modelos modelo;
  private boolean disponible;
  private double kmEnViaje;
  private double kmDsdMantenimiento; 

  public String getPatente() {
    return patente;
  }

  public void setPatente(String patente) {
    this.patente = patente;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Modelos getModelo() {
    return modelo;
  }

  public void setModelo(Modelos modelo) {
    this.modelo = modelo;
  }

  public boolean isDisponible() {
    return disponible;
  }

  public void setDisponible(boolean disponible) {
    this.disponible = disponible;
  }

  public double getKmEnViaje() {
    return kmEnViaje;
  }

  public void setKmEnViaje(double kmEnViaje) {
    this.kmEnViaje = kmEnViaje;
  }

  public double getKmDsdMantenimiento() {
    return kmDsdMantenimiento;
  }

  public void setKmDsdMantenimiento(double kmDsdMantenimiento) {
    this.kmDsdMantenimiento = kmDsdMantenimiento;
  }

  public Bicicletas() {
  }

  public Bicicletas(int id, String patente, String descripcion, Modelos modelo, boolean disponible, double kmEnViaje, double kmDsdMantenimiento) {
    this.id = id;
    this.patente = patente;
    this.descripcion = descripcion;
    this.modelo = modelo;
    this.disponible = disponible;
    this.kmEnViaje = kmEnViaje;
    this.kmDsdMantenimiento = kmDsdMantenimiento;
  }

  public Bicicletas(String patente, String descripcion, Modelos modelo, boolean disponible, double kmEnViaje, double kmDsdMantenimiento) {
    this.patente = patente;
    this.descripcion = descripcion;
    this.modelo = modelo;
    this.disponible = disponible;
    this.kmEnViaje = kmEnViaje;
    this.kmDsdMantenimiento = kmDsdMantenimiento;
  }

  public void sumarKmRecorrisoa(double dif) {
    this.kmDsdMantenimiento += dif;
    this.kmEnViaje += dif;
  }
  
  
}
