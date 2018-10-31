package Entidades;

import java.util.HashMap;
import java.util.Map;

public enum EstadosReserva {
  PENDIENTE(1), //CREA RESERVA
  ENCURSO(2), //INICIA RESERVA
  CANCELADA(3), //CANCELA RESERVA EL USUARIO
  FINALIZADA(4), // FINALIZA RESERVA SIN PROBLEMAS
  ELIMINADA(5), //CANCELA/ELIMINA RESERVA EL ADMINISTRADOR
  FALLAS(6), // FINALIZA CON PROBLEMAS
  DESCONOCIDO(0); //CUALQUIER OTRO CASO
  
  private static final Map<Integer, EstadosReserva> MY_MAP = new HashMap<Integer, EstadosReserva>();
  static {
    for (EstadosReserva myEnum : values()) {
      MY_MAP.put(myEnum.getId(), myEnum);
    }
  }
    
  private final int id;

  private EstadosReserva(int id) {
      this.id = id;
  }

  public int getId() {
      return id;
  }
  
  public static EstadosReserva getXId(int id) {
    return MY_MAP.get(id);
  }

}
