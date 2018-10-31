package Entidades;

import java.util.Date;

public class DetallesMantenimiento {
  private Mantenimientos mantenimiento;
  private TiposMantenimiento tipo;
  private boolean completado;

  public boolean isCompletado() {
    return completado;
  }

  public void setCompletado(boolean completado) {
    this.completado = completado;
  }
  
  public Mantenimientos getMantenimiento() {
    return mantenimiento;
  }

  public void setMantenimiento(Mantenimientos mantenimiento) {
    this.mantenimiento = mantenimiento;
  }

  public TiposMantenimiento getTipo() {
    return tipo;
  }

  public void setTipo(TiposMantenimiento tipo) {
    this.tipo = tipo;
  }
  
  public DetallesMantenimiento() {    
  }

  public DetallesMantenimiento(Mantenimientos mantenimiento, TiposMantenimiento tipo, boolean completado) {
    this.mantenimiento = mantenimiento;
    this.tipo = tipo;
    this.completado = completado;
  }
  
}
