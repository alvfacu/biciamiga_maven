package Negocio;

import Datos.CatalogoBicicletas;
import Datos.CatalogoModelos;
import Datos.CatalogoTiposBicicletas;
import Entidades.Bicicletas;
import Entidades.TiposBicicleta;
import Entidades.Modelos;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ControladorBicicletas {
  CatalogoBicicletas cb;
  CatalogoTiposBicicletas ctb;
  CatalogoModelos cm;

  public ControladorBicicletas() {
    this.cb = new CatalogoBicicletas();
    this.ctb = new CatalogoTiposBicicletas();
    this.cm = new CatalogoModelos();
  }

  public ArrayList<Bicicletas> getBicicletas() {
    return cb.getBicicletas();
  }
   
  public Bicicletas getBicicleta(int idBici) {
    return cb.getBicicleta(idBici);
  }
  
  public Bicicletas getBicicletaDisponible(int idBici) {
    return cb.getBicicletaDisponible(idBici);
  }

  public void altaBicicleta(Bicicletas b) {
    cb.altaBicicleta(b);
  }

  public void bajaBicicleta(Bicicletas b) {
    cb.bajaBicicleta(b);
  }

  public void modificarBicicleta(Bicicletas b) {
    cb.modificarBicicleta(b);
  }
  
  public ArrayList<Bicicletas> getBicicletaParaReserva(int idMod){
    return cb.getBicicletaParaReserva(idMod);
  }
  
  public ArrayList<Modelos> getModelosDisponibles(){
    return cm.getModelosDisponibles();
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXModelo(int modelo){
    return cm.getModelosDisponiblesXModelo2(modelo);
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXTipoXModelo(int tipo, int modelo,Calendar fechaDsd, Calendar fechaHst){
    return cm.getModelosDisponiblesXTipoXModelo2(tipo,modelo,fechaDsd,fechaHst);
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXTipo(int tipo,Calendar fechaDsd, Calendar fechaHst){
    return cm.getModelosDisponiblesXTipo2(tipo,fechaDsd,fechaHst);
  }
  
  public ArrayList<Modelos> getModelosDisponibles(Calendar fechaDsd, Calendar fechaHst) {
    return cm.getModelosDisponibles2(fechaDsd,fechaHst);
  }
  
  public int existePatente(String patente) {
    return cb.existeBicicleta(patente);
  }
      
  public ArrayList<TiposBicicleta> getTiposBicicleta() {
    return ctb.getTipos();
  }
   
  public TiposBicicleta getTipo(int id) {
    return ctb.getTipo(id);
  }

  public void altaTipoBicicleta(TiposBicicleta tb){
    ctb.altaTipoBicicleta(tb);
  }

  public void bajaTipoBicicleta(TiposBicicleta tb){
    ctb.bajaTipoBicicleta(tb);
  }

  public void modificarTipoBicicleta(TiposBicicleta tb) {
    ctb.modificarTipoBicicleta(tb);
  }

  public int existenBicicletasXTipo(String idModelo) {
    return cb.existenBicicletasXModelo(idModelo);
  }

  public ArrayList<Modelos> getModelos(){
    return cm.getModelos();
  }
  
  public Modelos getModelo(int id){
    return cm.getModelo(id);
  }
  
  public void altaModelo(Modelos m){
    cm.altaModelo(m);
  }

  public void bajaModelo(Modelos m){
    cm.bajaModelo(m);
  }
  
  public void modificarModelo(Modelos m){
    cm.modificarModelo(m);
  }
  
  public int existenModelosXTipo(String id){
    return cm.existenModelosXTipo(id);
  }
  
  public String dameUrl(String idModelo){
    return cm.dameUrl(idModelo);
  }
  
  public ArrayList<Bicicletas> getBicicletasParaMantenimiento(){
    return cb.getBicicletasParaMantenimiento();
  }

  public void habilitarBicicleta(boolean estado, Bicicletas bici) {
    cb.habilitarBicicleta(estado,bici);
  }
  
  public ArrayList<Modelos> getModelosXTipo(int tipo){
    return cm.getModelosXTipo2(tipo);
  }

  public boolean estaDisponibleParaReserva(Bicicletas b, Calendar desde, Calendar hasta) {
    return cb.estaDisponibleParaReserva(b,desde,hasta);
  }
  
  public boolean tieneReservasEnCurso(Bicicletas b){
    return cb.tieneReservasEnCurso(b.getId());
  }

}
