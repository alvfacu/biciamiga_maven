package Negocio;

import Datos.CatalogoMantenimientos;
import Datos.CatalogoTiposMantenimiento;
import Entidades.DetallesMantenimiento;
import Entidades.Mantenimientos;
import Entidades.TiposMantenimiento;
import java.util.ArrayList;

public class ControladorMantenimientos {
  CatalogoTiposMantenimiento ctm;
  CatalogoMantenimientos cm;

  public ControladorMantenimientos() {
    this.ctm = new CatalogoTiposMantenimiento();
    this.cm = new CatalogoMantenimientos();
  }

  public ArrayList<Mantenimientos> getMantenimientos() {
    return cm.getMantenimientos();
  }
   
  public Mantenimientos getMantenimiento(int id) {
    return cm.getMantenimiento(id);
  }

  public void altaMantenimiento(Mantenimientos m) {
    cm.altaMantenimiento(m);
  }

  public void bajaMantenimiento(Mantenimientos m){
    cm.bajaMantenimientos(m);
  }

  public void modificarMantenimiento(Mantenimientos m){
    cm.modificarMantenimiento(m);
  }

  public ArrayList<TiposMantenimiento> getTiposMantenimientos() {
    return ctm.getTipos();
  }
   
  public TiposMantenimiento getTipoMantenimiento(int id) {
    return ctm.getTipo(id);
  }

  public void altaTipoMantenimiento(TiposMantenimiento tm){
    ctm.altaTipoMantenimiento(tm);
  }

  public void bajaTipoMantenimiento(TiposMantenimiento tm){
    ctm.bajaTipoMantenimiento(tm);
  }

  public void modificarTipoMantenimiento(TiposMantenimiento tm) {
    ctm.modificarTipoMantenimiento(tm);
  }

  public int existenMantenimientosActivosXTipo(String id) {
    return ctm.existenMantenimientosActivosXTipo(id);
  }
  
  public ArrayList<Mantenimientos> getMantenimientosActivos(){
    return cm.getMantenimientosActivos();
  }
  
  public ArrayList<Mantenimientos> getMantenimientosFinalizados(){
    return cm.getMantenimientosFinalizados();
  }

  public Mantenimientos getMantenimientoActivo(int id) {
    return cm.getMantenimientoActivo(id);
  }
  
  public Mantenimientos getMantenimientoFinalizado(int id) {
    return cm.getMantenimientoFinalizado(id);
  }
  
  public ArrayList<DetallesMantenimiento> getDetallesXMantenimiento(int idMant){
    return cm.getDetallesXMantenimiento(idMant);
  }

  public DetallesMantenimiento getDetalleXMatenimiento(int idMant, int idTipo) {
    return cm.getDetalleXMatenimiento(idMant,idTipo);
  }
  
  public void altaDetalle(DetallesMantenimiento det) {
    cm.altaDetalle(det);
  }
  
  public void modificarDetalle(DetallesMantenimiento det) {
    cm.modificarDetalle(det);
  }

  public boolean tieneMantenimientoObligatorios(double km) {
    return ctm.tieneMantenimientosObligatorios(km);
  }
  
  public ArrayList<Mantenimientos> getMantenimientosXBici(int id){
    return cm.getMantenimientosXBici(id);
  }

}
