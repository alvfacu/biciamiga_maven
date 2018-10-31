package Datos;

import Entidades.Bicicletas;
import Entidades.EstadosReserva;
import Entidades.Reservas;
import Entidades.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CatalogoReservas {
  public ArrayList<Reservas> getReservas() {
    ArrayList<Reservas> reservas = new ArrayList<Reservas>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from reservas";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Reservas r = new Reservas();        
        r.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        r.setBici(b);
        Usuarios u = new CatalogoUsuarios().getUsuario(rs.getInt("id_usr"));
        r.setCliente(u);
        r.setEstado(EstadosReserva.getXId(rs.getInt("estado")));
        
        if(rs.getTimestamp("fecha_interna")!=null)
        {
          long fecha = rs.getTimestamp("fecha_interna").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInterna(currentDate);
        }
        else
        {
          r.setFechaInterna(null);
        }
      
        if(rs.getTimestamp("fecha_inicio_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioR(currentDate);
        }
        else
        {
          r.setFechaInicioR(null);
        }
        
        if(rs.getTimestamp("fecha_fin_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinR(currentDate);
        }
        else
        {
          r.setFechaFinR(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioP(currentDate);
        }
        else
        {
          r.setFechaInicioP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinP(currentDate);
        }
        else
        {
          r.setFechaFinP(null);
        }        
        
        
        r.setImporte(rs.getDouble("importe"));
        r.setKmRecorridos(rs.getDouble("km_totales"));
        r.setObservacion(rs.getString("obs"));
        reservas.add(r);
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
    return reservas;
  }

  public Reservas getReserva(int id) {    
    PreparedStatement sentencia;
    ResultSet rs;
    Reservas r = null;
    String sql = "select * from reservas where id=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        r = new Reservas();        
        r.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        r.setBici(b);
        Usuarios u = new CatalogoUsuarios().getUsuario(rs.getInt("id_usr"));
        r.setCliente(u);
        r.setEstado(EstadosReserva.getXId(rs.getInt("estado")));
        
        if(rs.getTimestamp("fecha_interna")!=null)
        {
          long fecha = rs.getTimestamp("fecha_interna").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInterna(currentDate);
        }
        else
        {
          r.setFechaInterna(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioP(currentDate);
        }
        else
        {
          r.setFechaInicioP(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioR(currentDate);
        }
        else
        {
          r.setFechaInicioR(null);
        }
        
        if(rs.getTimestamp("fecha_fin_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinP(currentDate);
        }
        else
        {
          r.setFechaFinP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinR(currentDate);
        }
        else
        {
          r.setFechaFinR(null);
        }
        
        r.setImporte(rs.getDouble("importe"));
        r.setKmRecorridos(rs.getDouble("km_totales"));
        r.setObservacion(rs.getString("obs"));     
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return r;
  }

  public void altaReserva(Reservas r) {
    PreparedStatement sentencia = null;
    ResultSet rs;
    String sql = "insert into reservas(id_bici,id_usr,estado,fecha_interna,fecha_inicio_pactada,fecha_fin_pactada,fecha_fin_real,importe,km_totales,obs,fecha_inicio_real) "
            + "values(?,?,?,?,?,?,?,?,?,?,?)";
    try {
      sentencia=ConexionBD.getInstancia().getconn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      sentencia.setInt(1,r.getBici().getId());
      sentencia.setInt(2,r.getCliente().getId());
      sentencia.setInt(3,r.getEstado().getId());
      sentencia.setTimestamp(4, new java.sql.Timestamp(r.getFechaInterna().getTime()));
      sentencia.setTimestamp(5, new java.sql.Timestamp(r.getFechaInicioP().getTime()));
      sentencia.setTimestamp(6, new java.sql.Timestamp(r.getFechaFinP().getTime()));
      if(r.getFechaFinR()!=null)
        sentencia.setTimestamp(7, new java.sql.Timestamp(r.getFechaFinR().getTime()));
      else
        sentencia.setTimestamp(7, null);
      sentencia.setDouble(8,r.getImporte());
      sentencia.setDouble(9,r.getKmRecorridos());
      sentencia.setString(10,r.getObservacion());
      if(r.getFechaInicioR()!=null)
        sentencia.setTimestamp(11, new java.sql.Timestamp(r.getFechaInicioR().getTime()));
      else
        sentencia.setTimestamp(11, null);
      sentencia.execute();
      rs=sentencia.getGeneratedKeys();
      if(rs!=null && rs.next()){
        r.setId(rs.getInt(1));
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

  public void bajaReserva(Reservas r) {
    PreparedStatement sentencia = null;
    String sql = "delete from reservas where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, r.getId());
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

  public void modificarReserva(Reservas r) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_inicio_real=?, fecha_fin_real=? "
            +"importe=?, km_totales=?, obs=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, r.getBici().getId());
      sentencia.setInt(2, r.getCliente().getId());
      sentencia.setInt(3, r.getEstado().getId());
      sentencia.setTimestamp(4, new java.sql.Timestamp(r.getFechaInterna().getTime()));
      sentencia.setTimestamp(5, new java.sql.Timestamp(r.getFechaInicioP().getTime()));
      sentencia.setTimestamp(6, new java.sql.Timestamp(r.getFechaFinP().getTime()));
      sentencia.setTimestamp(7, new java.sql.Timestamp(r.getFechaFinR().getTime()));
      sentencia.setDouble(8, r.getImporte());
      sentencia.setDouble(9, r.getImporte());
      sentencia.setString(10, r.getObservacion());
      sentencia.setInt(11,r.getId());
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

  public ArrayList<Reservas> getReservasNoFinalizadas(int id) {
    ArrayList<Reservas> reservas = new ArrayList<Reservas>();
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from reservas where fecha_fin_real is null and estado=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,id);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Reservas r = new Reservas();        
        r.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        r.setBici(b);
        Usuarios u = new CatalogoUsuarios().getUsuario(rs.getInt("id_usr"));
        r.setCliente(u);
        r.setEstado(EstadosReserva.getXId(rs.getInt("estado")));
        
        if(rs.getTimestamp("fecha_interna")!=null)
        {
          long fecha = rs.getTimestamp("fecha_interna").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInterna(currentDate);
        }
        else
        {
          r.setFechaInterna(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioR(currentDate);
        }
        else
        {
          r.setFechaInicioR(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioP(currentDate);
        }
        else
        {
          r.setFechaInicioP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinP(currentDate);
        }
        else
        {
          r.setFechaFinP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinR(currentDate);
        }
        else
        {
          r.setFechaFinR(null);
        }
        
        r.setImporte(rs.getDouble("importe"));
        r.setKmRecorridos(rs.getDouble("km_totales"));
        r.setObservacion(rs.getString("obs"));
        reservas.add(r);
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
    return reservas;
  }
  
  public ArrayList<Reservas> getReservasFinalizadas2() {
    ArrayList<Reservas> reservas = new ArrayList<Reservas>();
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from reservas where fecha_fin_real is not null and estado IN (?,?,?,?,?)";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,EstadosReserva.CANCELADA.getId());
      sentencia.setInt(2,EstadosReserva.FINALIZADA.getId());
      sentencia.setInt(3,EstadosReserva.FALLAS.getId());
      sentencia.setInt(4,EstadosReserva.ELIMINADA.getId());
      sentencia.setInt(5,EstadosReserva.DESCONOCIDO.getId());
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Reservas r = new Reservas();        
        r.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        r.setBici(b);
        Usuarios u = new CatalogoUsuarios().getUsuario(rs.getInt("id_usr"));
        r.setCliente(u);
        r.setEstado(EstadosReserva.getXId(rs.getInt("estado")));
        
        if(rs.getTimestamp("fecha_interna")!=null)
        {
          long fecha = rs.getTimestamp("fecha_interna").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInterna(currentDate);
        }
        else
        {
          r.setFechaInterna(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioP(currentDate);
        }
        else
        {
          r.setFechaInicioP(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioR(currentDate);
        }
        else
        {
          r.setFechaInicioR(null);
        }
        
        if(rs.getTimestamp("fecha_fin_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinP(currentDate);
        }
        else
        {
          r.setFechaFinP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinR(currentDate);
        }
        else
        {
          r.setFechaFinR(null);
        }
        
        r.setImporte(rs.getDouble("importe"));
        r.setKmRecorridos(rs.getDouble("km_totales"));
        r.setObservacion(rs.getString("obs"));
        reservas.add(r);
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
    return reservas;
  }
  
  public ArrayList<Reservas> getReservasPendientesXUsr2(int id) {
    ArrayList<Reservas> reservas = new ArrayList<Reservas>();
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from reservas where fecha_fin_real is null and estado=? and id_usr=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,EstadosReserva.PENDIENTE.getId());
      sentencia.setInt(2,id);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Reservas r = new Reservas();        
        r.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        r.setBici(b);
        Usuarios u = new CatalogoUsuarios().getUsuario(rs.getInt("id_usr"));
        r.setCliente(u);
        r.setEstado(EstadosReserva.getXId(rs.getInt("estado")));
        
        if(rs.getTimestamp("fecha_interna")!=null)
        {
          long fecha = rs.getTimestamp("fecha_interna").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInterna(currentDate);
        }
        else
        {
          r.setFechaInterna(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioP(currentDate);
        }
        else
        {
          r.setFechaInicioP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinP(currentDate);
        }
        else
        {
          r.setFechaFinP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinR(currentDate);
        }
        else
        {
          r.setFechaFinR(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioR(currentDate);
        }
        else
        {
          r.setFechaInicioR(null);
        }
        
        r.setImporte(rs.getDouble("importe"));
        r.setKmRecorridos(rs.getDouble("km_totales"));
        r.setObservacion(rs.getString("obs"));
        reservas.add(r);
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
    return reservas;
  }
  
  public ArrayList<Reservas> getReservasFinalizadasXUsr2(int id) {
    ArrayList<Reservas> reservas = new ArrayList<Reservas>();
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from reservas where fecha_fin_real is not null and estado IN (?,?,?) and id_usr=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,EstadosReserva.CANCELADA.getId());
      sentencia.setInt(2,EstadosReserva.FINALIZADA.getId());
      sentencia.setInt(3,EstadosReserva.DESCONOCIDO.getId());
      sentencia.setInt(4,id);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Reservas r = new Reservas();        
        r.setId(rs.getInt("id"));
        Bicicletas b = new CatalogoBicicletas().getBicicleta(rs.getInt("id_bici"));
        r.setBici(b);
        Usuarios u = new CatalogoUsuarios().getUsuario(rs.getInt("id_usr"));
        r.setCliente(u);
        r.setEstado(EstadosReserva.getXId(rs.getInt("estado")));
        
        if(rs.getTimestamp("fecha_interna")!=null)
        {
          long fecha = rs.getTimestamp("fecha_interna").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInterna(currentDate);
        }
        else
        {
          r.setFechaInterna(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioP(currentDate);
        }
        else
        {
          r.setFechaInicioP(null);
        }
        
        if(rs.getTimestamp("fecha_fin_pactada")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_pactada").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinP(currentDate);
        }
        else
        {
          r.setFechaFinP(null);
        }
        
        if(rs.getTimestamp("fecha_inicio_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_inicio_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaInicioR(currentDate);
        }
        else
        {
          r.setFechaInicioR(null);
        }
        
        if(rs.getTimestamp("fecha_fin_real")!=null)
        {
          long fecha = rs.getTimestamp("fecha_fin_real").getTime();
          Date currentDate = new Date(fecha);
          r.setFechaFinR(currentDate);
        }
        else
        {
          r.setFechaFinR(null);
        }
        
        r.setImporte(rs.getDouble("importe"));
        r.setKmRecorridos(rs.getDouble("km_totales"));
        r.setObservacion(rs.getString("obs"));
        reservas.add(r);
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
    return reservas;
  }

  public void cancelarReserva(int id) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_inicio_real=?, fecha_fin_real=?, importe=?, km_totales=?, obs=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.CANCELADA.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setDouble(4, 0);
      sentencia.setDouble(5, 0);
      sentencia.setString(6, "RESERVA CANCELADA POR EL USUARIO");
      sentencia.setInt(7, id);
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

  public void iniciarReserva(int id) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_inicio_real=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.ENCURSO.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setInt(3, id);
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

  public void eliminarReserva(int id) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_inicio_real=?, fecha_fin_real=?, importe=?, km_totales=?, obs=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.ELIMINADA.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setDouble(4, 0);
      sentencia.setDouble(5, 0);
      sentencia.setString(6, "RESERVA ELIMINADA POR ADMINISTRADOR");
      sentencia.setInt(7, id);
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

  public void finalizarReserva(Reservas r) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_fin_real=?, km_totales=?, obs=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.FINALIZADA.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(r.getFechaFinR().getTime()));
      sentencia.setDouble(3, r.getKmRecorridos());
      sentencia.setString(4, r.getObservacion());
      sentencia.setInt(5, r.getId());
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

  public void fallasReserva(Reservas r) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_fin_real=?, km_totales=?, obs=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.FALLAS.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(r.getFechaFinR().getTime()));
      sentencia.setDouble(3, r.getKmRecorridos());
      sentencia.setString(4, r.getObservacion());
      sentencia.setInt(5, r.getId());
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

  public void eliminarReservasXBici(int id) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_inicio_real=?, fecha_fin_real=?, importe=?, km_totales=?, obs=? where id_bici=? and estado!=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.ELIMINADA.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setDouble(4, 0);
      sentencia.setDouble(5, 0);
      sentencia.setString(6, "RESERVA ELIMINADA POR ELIMINAR BICICLETA");
      sentencia.setInt(7, id);
      sentencia.setInt(8, EstadosReserva.ELIMINADA.getId());
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

  void eliminarReservasXUsr(int id) {
    PreparedStatement sentencia = null;
    String sql = "update reservas set estado=?, fecha_inicio_real=?, fecha_fin_real=?, importe=?, km_totales=?, obs=? where id_usr=? and estado!=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, EstadosReserva.ELIMINADA.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
      sentencia.setDouble(4, 0);
      sentencia.setDouble(5, 0);
      sentencia.setString(6, "RESERVA ELIMINADA POR ELIMINAR USUARIO");
      sentencia.setInt(7, id);
      sentencia.setInt(8, EstadosReserva.ELIMINADA.getId());
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
