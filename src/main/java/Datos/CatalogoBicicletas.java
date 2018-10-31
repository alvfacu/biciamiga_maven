package Datos;

import Entidades.Bicicletas;
import Entidades.EstadosReserva;
import Entidades.Modelos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class CatalogoBicicletas {

  public ArrayList<Bicicletas> getBicicletas() {
    ArrayList<Bicicletas> bicicletas = new ArrayList<Bicicletas>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from bicicletas where baja=0";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Bicicletas b = new Bicicletas();
        b.setId(rs.getInt("id"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setPatente(rs.getString("patente"));
        b.setDisponible(rs.getBoolean("disponible"));
        b.setKmDsdMantenimiento(rs.getDouble("km_dsd_mantenimiento"));
        b.setKmEnViaje(rs.getDouble("km_viaje"));
        Modelos modelo = new CatalogoModelos().getModelo(rs.getInt("id_modelo"));
        b.setModelo(modelo);
        bicicletas.add(b);
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
    return bicicletas;
  }

  public Bicicletas getBicicleta(int id) {    
    PreparedStatement sentencia;
    ResultSet rs;
    Bicicletas b = null;
    String sql = "select * from bicicletas where id=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        b = new Bicicletas();
        b.setId(rs.getInt("id"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setPatente(rs.getString("patente"));
        b.setDisponible(rs.getBoolean("disponible"));
        b.setKmDsdMantenimiento(rs.getDouble("km_dsd_mantenimiento"));
        b.setKmEnViaje(rs.getDouble("km_viaje"));
        Modelos modelo = new CatalogoModelos().getModelo(rs.getInt("id_modelo"));
        b.setModelo(modelo);
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return b;
  }

  public void altaBicicleta(Bicicletas b) {
    PreparedStatement sentencia = null;
    ResultSet rs;
    String sql = "insert into bicicletas(patente,descripcion,disponible,km_dsd_mantenimiento,km_viaje,id_modelo) "
            + "values(?,?,?,?,?,?)";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      sentencia.setString(1,b.getPatente());
      sentencia.setString(2,b.getDescripcion());
      sentencia.setBoolean(3,b.isDisponible());
      sentencia.setDouble(4,b.getKmDsdMantenimiento());
      sentencia.setDouble(5,b.getKmEnViaje());
      sentencia.setInt(6,b.getModelo().getId());
      sentencia.execute();
      rs=sentencia.getGeneratedKeys();
      if(rs!=null && rs.next()){
        b.setId(rs.getInt(1));
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

  public void bajaBicicleta(Bicicletas b) {
    PreparedStatement sentencia = null;
    String sql = "update bicicletas set baja=1, disponible=0 where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, b.getId());
      sentencia.execute();
      
      new CatalogoReservas().eliminarReservasXBici(b.getId());
      
      new CatalogoMantenimientos().eliminarMantenimientosXBici(b.getId());
      
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

  public void modificarBicicleta(Bicicletas b) {
    PreparedStatement sentencia = null;
    String sql = "update bicicletas set descripcion=?,disponible=?,km_dsd_mantenimiento=?,km_viaje=?, id_modelo=?, patente=?"
            + " where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, b.getDescripcion());
      sentencia.setBoolean(2, b.isDisponible());
      sentencia.setDouble(3, b.getKmDsdMantenimiento());
      sentencia.setDouble(4, b.getKmEnViaje());
      sentencia.setInt(5, b.getModelo().getId());
      sentencia.setString(6, b.getPatente());
      sentencia.setInt(7, b.getId());
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

  public int existeBicicleta(String patente) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select count(*) from bicicletas where patente=? and baja=0";
    int cont = 0;
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, patente);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        cont = rs.getInt(1);      
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return cont;
  }

  public int existenBicicletasXModelo(String idModelo) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select count(*) from bicicletas where id_modelo=? and baja=0";
    int cont = 0;
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, idModelo);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        cont = rs.getInt(1);     
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return cont;
  }

  public ArrayList<Bicicletas> getBicicletasParaMantenimiento() {
    ArrayList<Bicicletas> bicicletas = new ArrayList<Bicicletas>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from bicicletas where disponible=true and baja=0";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Bicicletas b = new Bicicletas();
        if(!tieneReservasEnCurso(rs.getInt("id")))
        {
          b.setId(rs.getInt("id"));
          b.setDescripcion(rs.getString("descripcion"));
          b.setPatente(rs.getString("patente"));
          b.setDisponible(rs.getBoolean("disponible"));
          b.setKmDsdMantenimiento(rs.getDouble("km_dsd_mantenimiento"));
          b.setKmEnViaje(rs.getDouble("km_viaje"));
          Modelos modelo = new CatalogoModelos().getModelo(rs.getInt("id_modelo"));
          b.setModelo(modelo);
          bicicletas.add(b);
        }
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
    return bicicletas;
  }

  public void habilitarBicicleta(boolean estado, Bicicletas bici) {
    PreparedStatement sentencia = null;
    String sql = "update bicicletas set disponible=? where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setBoolean(1, estado);
      sentencia.setInt(2, bici.getId());
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

  public Bicicletas getBicicletaDisponible(int idBici) {
    PreparedStatement sentencia;
    ResultSet rs;
    Bicicletas b = null;
    String sql = "select * from bicicletas where id=? and disponible=true and baja=0";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, idBici);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        b = new Bicicletas();
        b.setId(rs.getInt("id"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setPatente(rs.getString("patente"));
        b.setDisponible(rs.getBoolean("disponible"));
        b.setKmDsdMantenimiento(rs.getDouble("km_dsd_mantenimiento"));
        b.setKmEnViaje(rs.getDouble("km_viaje"));
        Modelos modelo = new CatalogoModelos().getModelo(rs.getInt("id_modelo"));
        b.setModelo(modelo);
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return b;
  }

  public ArrayList<Bicicletas> getBicicletaParaReserva(int idMod) {
    PreparedStatement sentencia;
    ResultSet rs;
    ArrayList<Bicicletas> bicicletas = null;
    String sql = "select * from bicicletas where id_modelo=? and disponible=true and baja=0 order by km_dsd_mantenimiento asc";
    
    try {
      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, idMod);
      rs = sentencia.executeQuery();
      bicicletas = new ArrayList<Bicicletas>();
      while (rs.next()) {
        Bicicletas b  = new Bicicletas();
        b.setId(rs.getInt("id"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setPatente(rs.getString("patente"));
        b.setDisponible(rs.getBoolean("disponible"));
        b.setKmDsdMantenimiento(rs.getDouble("km_dsd_mantenimiento"));
        b.setKmEnViaje(rs.getDouble("km_viaje"));
        Modelos modelo = new CatalogoModelos().getModelo(rs.getInt("id_modelo"));
        b.setModelo(modelo);
        bicicletas.add(b);
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return bicicletas;
  }

  public boolean estaDisponibleParaReserva(Bicicletas b, Calendar desde, Calendar hasta) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select * from reservas WHERE id_bici=? AND fecha_fin_pactada>=? AND fecha_inicio_pactada<=? AND estado IN (?,?)";
    boolean bnd = true;
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,b.getId());
      sentencia.setTimestamp(2, new java.sql.Timestamp(desde.getTimeInMillis()));
      sentencia.setTimestamp(3, new java.sql.Timestamp(hasta.getTimeInMillis()));
      sentencia.setInt(4, EstadosReserva.ENCURSO.getId());
      sentencia.setInt(5, EstadosReserva.PENDIENTE.getId());      
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        bnd=false;
        return bnd;
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return bnd;
  }

  Iterable<Bicicletas> getBicicletasXModelo(int id) {
    ArrayList<Bicicletas> bicis = new ArrayList<Bicicletas>();
    PreparedStatement sentencia;
    ResultSet rs;
    String sql = "select * from bicicletas WHERE id_modelo=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Bicicletas b = new Bicicletas();
        b.setId(rs.getInt("id"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setPatente(rs.getString("patente"));
        b.setDisponible(rs.getBoolean("disponible"));
        b.setKmDsdMantenimiento(rs.getDouble("km_dsd_mantenimiento"));
        b.setKmEnViaje(rs.getDouble("km_viaje"));
        Modelos modelo = new CatalogoModelos().getModelo(rs.getInt("id_modelo"));
        b.setModelo(modelo);
        bicis.add(b);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      ConexionBD.getInstancia().CloseConn();
    }
    return bicis;
  }
  
  public boolean tieneReservasEnCurso(int idbici) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select * from reservas WHERE id_bici=? AND estado IN (?)";
    boolean bnd = false;
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1,idbici);
      sentencia.setInt(2, EstadosReserva.ENCURSO.getId());     
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        bnd=true;
        return bnd;
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return bnd;
  }

  
}
