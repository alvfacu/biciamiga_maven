package Datos;

import Entidades.Bicicletas;
import Entidades.DetallesMantenimiento;
import Entidades.Mantenimientos;
import Entidades.TiposMantenimiento;
import Negocio.ControladorMantenimientos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CatalogoMantenimientos {
  public ArrayList<Mantenimientos> getMantenimientos() {
    ArrayList<Mantenimientos> mantenimientos = new ArrayList<Mantenimientos>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from mantenimientos";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Mantenimientos m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        
        //java.sql.Timestamp fecha_ingreso = rs.getTimestamp("fecha_ingreso");
        //m.setFechaIngreso(fecha_ingreso);
        if(rs.getTimestamp("fecha_ingreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_ingreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaIngreso(currentDate);
        }
        else
        {
          m.setFechaIngreso(null);
        }
        
        if(rs.getTimestamp("fecha_egreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_egreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaEgreso(currentDate);
        }
        else
        {
          m.setFechaEgreso(null);
        }
        
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setKmParciales(rs.getDouble("km_parciales"));
        m.setObservacion(rs.getString("obs"));
        mantenimientos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (sentencia != null) {
          sentencia.close();
        }
        if (rs != null) {
          rs.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return mantenimientos;
  }

  public Mantenimientos getMantenimiento(int id) {    
    PreparedStatement sentencia;
    ResultSet rs;
    Mantenimientos m = null;
    String sql = "select * from mantenimientos where id=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        m.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        m.setFechaEgreso(rs.getTimestamp("fecha_egreso"));
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setKmParciales(rs.getDouble("km_parciales"));
        m.setObservacion(rs.getString("obs"));
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return m;
  }

  public void altaMantenimiento(Mantenimientos m) {
    PreparedStatement sentencia = null;
    ResultSet rs;
    String sql = "insert into mantenimientos(id_bici,fecha_ingreso,km_ingreso,fecha_egreso,km_egreso,obs,km_parciales) "
            + "values(?,?,?,?,?,?,?)";
    try {
      sentencia=ConexionBD.getInstancia().getconn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      sentencia.setInt(1,m.getBici().getId());      
      
      sentencia.setTimestamp(2, new java.sql.Timestamp(m.getFechaIngreso().getTime()));
      sentencia.setDouble(3, m.getKmIngreso());
      
      if(m.getFechaEgreso()!=null)
        sentencia.setTimestamp(4, new java.sql.Timestamp(m.getFechaEgreso().getTime()));
      else
        sentencia.setTimestamp(4, null);
      
      sentencia.setDouble(5, m.getKmEgreso());
      sentencia.setString(6, m.getObservacion());
      sentencia.setDouble(7, m.getKmIngreso());
      sentencia.execute();
      rs=sentencia.getGeneratedKeys();
      if(rs!=null && rs.next()){
        m.setId(rs.getInt(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException sqle) {
        sqle.printStackTrace();
      }
    }
  }

  public void bajaMantenimientos(Mantenimientos m) {
    PreparedStatement sentencia = null;
    //String sql = "delete from mantenimientos where id=?";
    String sql = "update mantenimientos set baja=1, obs=?, fecha_egreso=now() where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, "MANTENIMIENTO ELIMINADO");
      sentencia.setInt(2, m.getId());
      sentencia.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();

      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }

  }

  public void modificarMantenimiento(Mantenimientos m) {
    PreparedStatement sentencia = null;
    String sql = "update mantenimientos set fecha_egreso=?, km_egreso=?, km_parciales=?, obs=? "
            +" where id=? and id_bici=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      if(m.getFechaEgreso()!=null)
        sentencia.setTimestamp(1, new java.sql.Timestamp(m.getFechaEgreso().getTime()));
      else
        sentencia.setTimestamp(1, null);      
      sentencia.setDouble(2, m.getKmEgreso());
      sentencia.setDouble(3, m.getKmParciales());
      sentencia.setString(4, m.getObservacion());
      sentencia.setInt(5,m.getId());
      sentencia.setInt(6,m.getBici().getId());
      sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }

  public ArrayList<Mantenimientos> getMantenimientosActivos() {
    ArrayList<Mantenimientos> mantenimientos = new ArrayList<Mantenimientos>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from mantenimientos where fecha_egreso is null and baja=0";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Mantenimientos m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        //java.sql.Timestamp fecha_ingreso = rs.getTimestamp("fecha_ingreso");
        //m.setFechaIngreso(fecha_ingreso);
        if(rs.getTimestamp("fecha_ingreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_ingreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaIngreso(currentDate);
        }
        else
        {
          m.setFechaIngreso(null);
        }
        
        if(rs.getTimestamp("fecha_egreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_egreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaEgreso(currentDate);
        }
        else
        {
          m.setFechaEgreso(null);
        }
        
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setKmParciales(rs.getDouble("km_parciales"));
        m.setObservacion(rs.getString("obs"));
        mantenimientos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (sentencia != null) {
          sentencia.close();
        }
        if (rs != null) {
          rs.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return mantenimientos;
  }

  public Mantenimientos getMantenimientoActivo(int id) {
    PreparedStatement sentencia;
    ResultSet rs;
    Mantenimientos m = null;
    String sql = "select * from mantenimientos where id=? and fecha_egreso is null and baja=0";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        m.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        m.setFechaEgreso(rs.getTimestamp("fecha_egreso"));
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setKmParciales(rs.getDouble("km_parciales"));
        m.setObservacion(rs.getString("obs"));
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return m;
  }
  
  public ArrayList<Mantenimientos> getMantenimientosFinalizados() {
        ArrayList<Mantenimientos> mantenimientos = new ArrayList<Mantenimientos>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from mantenimientos where fecha_egreso is not null or baja=1";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Mantenimientos m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        //java.sql.Timestamp fecha_ingreso = rs.getTimestamp("fecha_ingreso");
        //m.setFechaIngreso(fecha_ingreso);
        if(rs.getTimestamp("fecha_ingreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_ingreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaIngreso(currentDate);
        }
        else
        {
          m.setFechaIngreso(null);
        }
        
        if(rs.getTimestamp("fecha_egreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_egreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaEgreso(currentDate);
        }
        else
        {
          m.setFechaEgreso(null);
        }
        
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setObservacion(rs.getString("obs"));
        mantenimientos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (sentencia != null) {
          sentencia.close();
        }
        if (rs != null) {
          rs.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return mantenimientos;
  }
  
  public void altaDetalle(DetallesMantenimiento det) {
    PreparedStatement sentencia = null;
    String sql = "insert into detalle_mantenimiento(id,id_tipom,completado) "
            + "values(?,?,?)";
    try {
      sentencia=ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,det.getMantenimiento().getId());      
      sentencia.setInt(2,det.getTipo().getId());      
      sentencia.setBoolean(3, det.isCompletado());
      sentencia.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException sqle) {
        sqle.printStackTrace();
      }
    }
  }

  public ArrayList<DetallesMantenimiento> getDetallesXMantenimiento(int idMant) {
    ArrayList<DetallesMantenimiento> detalle = new ArrayList<DetallesMantenimiento>();
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from detalle_mantenimiento where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, idMant);
      rs = sentencia.executeQuery();
      
      while (rs.next()) {
        DetallesMantenimiento det = new DetallesMantenimiento();
        TiposMantenimiento tipo = new ControladorMantenimientos().getTipoMantenimiento(rs.getInt("id_tipom"));
        det.setTipo(tipo);
        Mantenimientos mant = new ControladorMantenimientos().getMantenimiento(rs.getInt("id"));
        det.setMantenimiento(mant);
        det.setCompletado(rs.getBoolean("completado"));
        detalle.add(det);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (sentencia != null) {
          sentencia.close();
        }
        if (rs != null) {
          rs.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return detalle;
  }

  public DetallesMantenimiento getDetalleXMatenimiento(int idMant, int idTipo) {
    DetallesMantenimiento det = null;
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from detalle_mantenimiento where id=? and id_tipom=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, idMant);
      sentencia.setInt(2, idTipo);
      rs = sentencia.executeQuery();
      
      if(rs.next()) {
        det = new DetallesMantenimiento();
        TiposMantenimiento tipo = new ControladorMantenimientos().getTipoMantenimiento(rs.getInt("id_tipom"));
        det.setTipo(tipo);
        Mantenimientos mant = new ControladorMantenimientos().getMantenimiento(rs.getInt("id"));
        det.setMantenimiento(mant);
        det.setCompletado(rs.getBoolean("completado"));
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (sentencia != null) {
          sentencia.close();
        }
        if (rs != null) {
          rs.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return det;
  }

  public void modificarDetalle(DetallesMantenimiento det) {
    PreparedStatement sentencia = null;
    String sql = "update detalle_mantenimiento set completado=? where id=? and id_tipom=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setBoolean(1, det.isCompletado());
      sentencia.setInt(2, det.getMantenimiento().getId());
      sentencia.setInt(3, det.getTipo().getId());
      sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }

  public Mantenimientos getMantenimientoFinalizado(int id) {
        PreparedStatement sentencia;
    ResultSet rs;
    Mantenimientos m = null;
    String sql = "select * from mantenimientos where id=? and (fecha_egreso is not null or baja=1)";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        m.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        m.setFechaEgreso(rs.getTimestamp("fecha_egreso"));
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setKmParciales(rs.getDouble("km_parciales"));
        m.setObservacion(rs.getString("obs"));
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return m;
  }

  public ArrayList<Mantenimientos> getMantenimientosXBici(int id){
    ArrayList<Mantenimientos> mantenimientos = new ArrayList<Mantenimientos>();
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from mantenimientos where id_bici=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Mantenimientos m = new Mantenimientos();
        m.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        m.setBici(b);
        //java.sql.Timestamp fecha_ingreso = rs.getTimestamp("fecha_ingreso");
        //m.setFechaIngreso(fecha_ingreso);
        if(rs.getTimestamp("fecha_ingreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_ingreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaIngreso(currentDate);
        }
        else
        {
          m.setFechaIngreso(null);
        }
        
        if(rs.getTimestamp("fecha_egreso")!=null)
        {
          long fecha = rs.getTimestamp("fecha_egreso").getTime();
          Date currentDate = new Date(fecha);
          m.setFechaEgreso(currentDate);
        }
        else
        {
          m.setFechaEgreso(null);
        }
        
        m.setKmIngreso(rs.getDouble("km_ingreso"));
        m.setKmEgreso(rs.getDouble("km_egreso"));
        m.setKmParciales(rs.getDouble("km_parciales"));
        m.setObservacion(rs.getString("obs"));
        mantenimientos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      try {
        if (sentencia != null) {
          sentencia.close();
        }
        if (rs != null) {
          rs.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
    return mantenimientos;
  }

  void eliminarMantenimientosXBici(int id) {
    PreparedStatement sentencia = null;
    String sql = "update mantenimientos set baja=1, obs=?, fecha_egreso=now() where id_bici=? and fecha_egreso is null and baja=0";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, "MANTENIMIENTO ELIMINADO POR ELIMINAR BICICLETA");
      sentencia.setInt(2, id);      
      sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }

  void bajaMantenimientosXTipoMantenimiento(int id) {
    PreparedStatement sentencia = null;
    String sql = "update mantenimientos set baja=1, obs=?, fecha_egreso=now() where id=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, "MANTENIMIENTO ELIMINADO POR ELIMINAR TIPO MANTENIMIENTO");
      sentencia.setInt(2, id);      
      sentencia.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (sentencia != null && !sentencia.isClosed()) {
          sentencia.close();
        }
        ConexionBD.getInstancia().CloseConn();
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }
  
  
}
