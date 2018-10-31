package Entidades;

public class TiposMantenimiento {
  
  private int id;
  private double kmParaMantenimiento;
  private boolean obligatorio;
  private String descripcion;
  private String nombre;

  public double getKmParaMantenimiento() {
    return kmParaMantenimiento;
  }

  public void setKmParaMantenimiento(double kmParaMantenimiento) {
    this.kmParaMantenimiento = kmParaMantenimiento;
  }

  public boolean isObligatorio() {
    return obligatorio;
  }

  public void setObligatorio(boolean obligatorio) {
    this.obligatorio = obligatorio;
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

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public TiposMantenimiento() {
  }

  public TiposMantenimiento(double kmParaMantenimiento, boolean obligatorio, String descripcion, String nombre) {
    this.kmParaMantenimiento = kmParaMantenimiento;
    this.obligatorio = obligatorio;
    this.descripcion = descripcion;
    this.nombre = nombre;
  }

  public TiposMantenimiento(int id, double kmParaMantenimiento, boolean obligatorio, String descripcion, String nombre) {
    this.id = id;
    this.kmParaMantenimiento = kmParaMantenimiento;
    this.obligatorio = obligatorio;
    this.descripcion = descripcion;
    this.nombre = nombre;
  }
  
  
  
}
