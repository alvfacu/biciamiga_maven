package Datos;

import Entidades.Bicicletas;
import Entidades.EstadosReserva;
import Entidades.Modelos;
import Entidades.TiposBicicleta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class CatalogoModelos {
  
  public ArrayList<Modelos> getModelos() {
    ArrayList<Modelos> modelos = new ArrayList<Modelos>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from modelos where baja=0";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos.add(m);
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
    return modelos;
  }

  public Modelos getModelo(int id) {    
    PreparedStatement sentencia;
    ResultSet rs;
    Modelos m = null;
    String sql = "select * from modelos where id=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return m;
  }

  public void altaModelo(Modelos m) {
    PreparedStatement sentencia = null;
    ResultSet rs;
    String sql = "insert into modelos(nombre,descrip,precio_hr,precio_dia,url1,url2,url3,id_tipo) "
            + "values(?,?,?,?,?,?,?,?)";
    try {
      sentencia=ConexionBD.getInstancia().getconn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      sentencia.setString(1,m.getNombre());
      sentencia.setString(2,m.getCaracteristicas_gral());
      sentencia.setDouble(3,m.getPrecioXHr());
      sentencia.setDouble(4,m.getPrecioXDia());
      sentencia.setString(5,m.getUrl1());
      sentencia.setString(6,m.getUrl2());
      sentencia.setString(7,m.getUrl3());
      sentencia.setInt(8,m.getTipo().getId());
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

  public void bajaModelo(Modelos m) {
    PreparedStatement sentencia = null;    
    try {
      
      String sql = "update modelos set baja=1 where id=?";
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, m.getId());
      sentencia.execute();
      
      for(Bicicletas b : new CatalogoBicicletas().getBicicletasXModelo(m.getId()))
        new CatalogoBicicletas().bajaBicicleta(b);

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

  public void modificarModelo(Modelos m) {
    PreparedStatement sentencia = null;
    String sql = "update modelos set nombre=?, descrip=?, precio_hr=?, precio_dia=?, url1=?, url2=?, url3=?, id_tipo=?"
            + " where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, m.getNombre());
      sentencia.setString(2, m.getCaracteristicas_gral());
      sentencia.setDouble(3, m.getPrecioXHr());
      sentencia.setDouble(4, m.getPrecioXDia());
      sentencia.setString(5, m.getUrl1());
      sentencia.setString(6, m.getUrl2());
      sentencia.setString(7, m.getUrl3());
      sentencia.setInt(8, m.getTipo().getId());
      sentencia.setInt(9, m.getId());
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
  
  public int existenModelosXTipo(String idTipo) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select count(*) from modelos where id_tipo=? and baja=0";
    int cont = 0;
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, idTipo);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        cont = rs.getInt(1);     
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return cont;
  }

  public String dameUrl(String idModelo) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select url1 from modelos where id=?";
    String url = "";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, idModelo);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        url = rs.getString(1);    
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return url;
  }

  public ArrayList<Modelos> getModelosDisponibles(Calendar fechaDsd, Calendar fechaHst) {
    ArrayList<Modelos> modelos_reservas = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_totales = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_listado = new ArrayList<Modelos>();
    
    Statement sentencia = null;
    ResultSet rs = null;
    
    String sql1 = "SELECT m.id, m.id_tipo, count(*) as cant FROM reservas r INNER JOIN bicicletas b ON r.id_bici=b.id "
            + "INNER JOIN modelos m ON m.id=b.id_modelo INNER JOIN tipos_bicicleta tp ON m.id_tipo=tp.id "
            + "WHERE fecha_fin_pactada>='"+new java.sql.Timestamp(fechaDsd.getTimeInMillis())+"' AND "
            + "fecha_inicio_pactada<='"+new java.sql.Timestamp(fechaHst.getTimeInMillis())+"' AND "
            + "r.estado="+EstadosReserva.PENDIENTE.getId()+" GROUP BY m.id";
    
    String sql2 = "SELECT m.*, count(*) as cant FROM modelos m INNER JOIN bicicletas b "
            + "ON b.id_modelo=m.id WHERE b.disponible=true AND b.baja=0 AND m.baja=0 GROUP BY m.id";
    
    try {      
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql1);
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_reservas.add(m);
      }
      
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql2);
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_totales.add(m);
      }
      
      for(Modelos m : modelos_totales){        
        int cant = m.getCant();
        Modelos mAct = null;
        for(Modelos mr : modelos_reservas){
          if(mr.getId()==m.getId() && mr.getTipo().getId()==m.getTipo().getId()){
            mAct = mr;
            break;
          }
        }
        if(mAct!=null){
          int cantR = mAct.getCant();
          if(cantR<cant)
            modelos_listado.add(m);
        } else
          modelos_listado.add(m);
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
    return modelos_listado;
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXTipo(int tipo,Calendar fechaDsd, Calendar fechaHst) {
    ArrayList<Modelos> modelos_reservas = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_totales = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_listado = new ArrayList<Modelos>();
    
    Statement sentencia = null;
    ResultSet rs = null;
    
    String sql1 = "SELECT m.id, m.id_tipo, count(*) as cant FROM reservas r INNER JOIN bicicletas b ON r.id_bici=b.id "
            + "INNER JOIN modelos m ON m.id=b.id_modelo INNER JOIN tipos_bicicleta tp ON m.id_tipo=tp.id "
            + "WHERE m.id_tipo="+tipo+" AND fecha_fin_pactada>='"+new java.sql.Timestamp(fechaDsd.getTimeInMillis())+"' AND "
            + "fecha_inicio_pactada<='"+new java.sql.Timestamp(fechaHst.getTimeInMillis())+"' AND "
            + "r.estado="+EstadosReserva.PENDIENTE.getId()+" GROUP BY m.id";
    
    String sql2 = "SELECT m.*, count(*) as cant FROM modelos m INNER JOIN bicicletas b "
            + "ON b.id_modelo=m.id WHERE b.disponible=true AND m.id_tipo="+tipo+" AND b.baja=0 AND m.baja=0 GROUP BY m.id";
    
    try {      
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql1);
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_reservas.add(m);
      }
      
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql2);
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_totales.add(m);
      }
      
      for(Modelos m : modelos_totales){        
        int cant = m.getCant();
        Modelos mAct = null;
        for(Modelos mr : modelos_reservas){
          if(mr.getId()==m.getId() && mr.getTipo().getId()==m.getTipo().getId()){
            mAct = mr;
            break;
          }
        }
        if(mAct!=null){
          int cantR = mAct.getCant();
          if(cantR<cant)
            modelos_listado.add(m);
        } else
          modelos_listado.add(m);
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
    return modelos_listado;
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXTipoXModelo(int tipo, int modelo,Calendar fechaDsd, Calendar fechaHst) {
    ArrayList<Modelos> modelos_reservas = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_totales = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_listado = new ArrayList<Modelos>();
    
    Statement sentencia = null;
    ResultSet rs = null;
    
    String sql1 = "SELECT m.id, m.id_tipo, count(*) as cant FROM reservas r INNER JOIN bicicletas b ON r.id_bici=b.id "
            + "INNER JOIN modelos m ON m.id=b.id_modelo INNER JOIN tipos_bicicleta tp ON m.id_tipo=tp.id "
            + "WHERE m.id_tipo="+tipo+" AND m.id="+modelo+" AND fecha_fin_pactada>='"+new java.sql.Timestamp(fechaDsd.getTimeInMillis())+"' AND "
            + "fecha_inicio_pactada<='"+new java.sql.Timestamp(fechaHst.getTimeInMillis())+"' AND "
            + "r.estado="+EstadosReserva.PENDIENTE.getId()+" GROUP BY m.id";
    
    String sql2 = "SELECT m.*, count(*) as cant FROM modelos m INNER JOIN bicicletas b "
            + "ON b.id_modelo=m.id WHERE b.disponible=true AND m.id="+modelo+" AND m.id_tipo="+tipo+" AND m.baja=0 AND b.baja=0 GROUP BY m.id";
    
    try {      
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql1);
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_reservas.add(m);
      }
      
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql2);
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_totales.add(m);
      }
      
      for(Modelos m : modelos_totales){        
        int cant = m.getCant();
        Modelos mAct = null;
        for(Modelos mr : modelos_reservas){
          if(mr.getId()==m.getId() && mr.getTipo().getId()==m.getTipo().getId()){
            mAct = mr;
            break;
          }
        }
        if(mAct!=null){
          int cantR = mAct.getCant();
          if(cantR<cant)
            modelos_listado.add(m);
        } else
          modelos_listado.add(m);
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
    return modelos_listado;
  }

  public ArrayList<Modelos> getModelosDisponibles() {
    ArrayList<Modelos> modelos = new ArrayList<Modelos>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select m.* from modelos m inner join bicicletas b "
            + "on b.id_modelo=m.id WHERE b.disponible=true and m.baja=0 and b.baja=0 GROUP BY m.id";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos.add(m);
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
    return modelos;
  }

  public ArrayList<Modelos> getModelosDisponiblesXModelo(int modelo) {
    ArrayList<Modelos> modelos = new ArrayList<Modelos>();
    Statement sentencia;
    ResultSet rs;
    String sql = "select m.* from modelos m inner join bicicletas b on b.id_modelo=m.id WHERE b.disponible=true and b.baja=0 and m.baja=0 AND m.id="+modelo+" GROUP BY m.id";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      ConexionBD.getInstancia().CloseConn();
    }
    return modelos;
  }

  public ArrayList<Modelos> getModelosXTipo(int tipo) {
    ArrayList<Modelos> modelos = new ArrayList<Modelos>();
    Statement sentencia;
    ResultSet rs;
    String sql = "select * from modelos WHERE id_tipo="+tipo;
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      ConexionBD.getInstancia().CloseConn();
    }
    return modelos;
  }
  
  public ArrayList<Modelos> getModelosDisponibles2(Calendar fechaDsd, Calendar fechaHst) {
    ArrayList<Modelos> modelos_reservas = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_totales = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_listado = new ArrayList<Modelos>();
    
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    
    String sql1 = "SELECT m.id, m.id_tipo, count(*) as cant FROM reservas r INNER JOIN bicicletas b ON r.id_bici=b.id "
            + "INNER JOIN modelos m ON m.id=b.id_modelo INNER JOIN tipos_bicicleta tp ON m.id_tipo=tp.id "
            + "WHERE fecha_fin_pactada>=? AND fecha_inicio_pactada<=? AND r.estado=? GROUP BY m.id";
    
    String sql2 = "SELECT m.*, count(*) as cant FROM modelos m INNER JOIN bicicletas b "
            + "ON b.id_modelo=m.id WHERE b.disponible=true and b.baja=0 and m.baja=0 GROUP BY m.id";
    
    try {      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql1);
      sentencia.setTimestamp(1, new java.sql.Timestamp(fechaDsd.getTimeInMillis()));
      sentencia.setTimestamp(2, new java.sql.Timestamp(fechaHst.getTimeInMillis()));
      sentencia.setInt(3, EstadosReserva.PENDIENTE.getId());      
      rs = sentencia.executeQuery();
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_reservas.add(m);
      }
      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql2);
      rs = sentencia.executeQuery();
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_totales.add(m);
      }
      
      for(Modelos m : modelos_totales){        
        int cant = m.getCant();
        Modelos mAct = null;
        for(Modelos mr : modelos_reservas){
          if(mr.getId()==m.getId() && mr.getTipo().getId()==m.getTipo().getId()){
            mAct = mr;
            break;
          }
        }
        if(mAct!=null){
          int cantR = mAct.getCant();
          if(cantR<cant)
            modelos_listado.add(m);
        } else
          modelos_listado.add(m);
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
    return modelos_listado;
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXTipo2(int tipo,Calendar fechaDsd, Calendar fechaHst) {
    ArrayList<Modelos> modelos_reservas = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_totales = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_listado = new ArrayList<Modelos>();
    
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    
    String sql1 = "SELECT m.id, m.id_tipo, count(*) as cant FROM reservas r INNER JOIN bicicletas b ON r.id_bici=b.id "
            + "INNER JOIN modelos m ON m.id=b.id_modelo INNER JOIN tipos_bicicleta tp ON m.id_tipo=tp.id "
            + "WHERE m.id_tipo=? AND fecha_fin_pactada>=? AND fecha_inicio_pactada<=? AND r.estado=? GROUP BY m.id";
    
    String sql2 = "SELECT m.*, count(*) as cant FROM modelos m INNER JOIN bicicletas b ON b.id_modelo=m.id WHERE b.disponible=true AND b.baja=0 AND m.baja=0 AND m.id_tipo=? GROUP BY m.id";
    
    try {      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql1);
      sentencia.setInt(1, tipo);
      sentencia.setTimestamp(2, new java.sql.Timestamp(fechaDsd.getTimeInMillis()));
      sentencia.setTimestamp(3, new java.sql.Timestamp(fechaHst.getTimeInMillis()));
      sentencia.setInt(4, EstadosReserva.PENDIENTE.getId());
      rs = sentencia.executeQuery();
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_reservas.add(m);
      }
      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql2);      
      sentencia.setInt(1, tipo);
      rs = sentencia.executeQuery();
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_totales.add(m);
      }
      
      for(Modelos m : modelos_totales){        
        int cant = m.getCant();
        Modelos mAct = null;
        for(Modelos mr : modelos_reservas){
          if(mr.getId()==m.getId() && mr.getTipo().getId()==m.getTipo().getId()){
            mAct = mr;
            break;
          }
        }
        if(mAct!=null){
          int cantR = mAct.getCant();
          if(cantR<cant)
            modelos_listado.add(m);
        } else
          modelos_listado.add(m);
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
    return modelos_listado;
  }
  
  public ArrayList<Modelos> getModelosDisponiblesXTipoXModelo2(int tipo, int modelo,Calendar fechaDsd, Calendar fechaHst) {
    ArrayList<Modelos> modelos_reservas = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_totales = new ArrayList<Modelos>();
    ArrayList<Modelos> modelos_listado = new ArrayList<Modelos>();
    
    PreparedStatement sentencia = null;
    ResultSet rs = null;
    
    String sql1 = "SELECT m.id, m.id_tipo, count(*) as cant FROM reservas r INNER JOIN bicicletas b ON r.id_bici=b.id "
            + "INNER JOIN modelos m ON m.id=b.id_modelo INNER JOIN tipos_bicicleta tp ON m.id_tipo=tp.id "
            + "WHERE m.id_tipo=? AND m.id=? AND fecha_fin_pactada>=? AND fecha_inicio_pactada<=? AND r.estado=? GROUP BY m.id";
    
    String sql2 = "SELECT m.*, count(*) as cant FROM modelos m INNER JOIN bicicletas b "
            + "ON b.id_modelo=m.id WHERE b.disponible=true AND b.baja=0 AND m.baja=0 AND m.id=? AND m.id_tipo=? GROUP BY m.id";
    
    try {      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql1);
      sentencia.setInt(1, tipo);
      sentencia.setInt(2, modelo);
      sentencia.setTimestamp(3, new java.sql.Timestamp(fechaDsd.getTimeInMillis()));
      sentencia.setTimestamp(4, new java.sql.Timestamp(fechaHst.getTimeInMillis()));
      sentencia.setInt(5, EstadosReserva.PENDIENTE.getId());
      rs = sentencia.executeQuery();
      
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_reservas.add(m);
      }
      
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql2);
      sentencia.setInt(1, modelo);
      sentencia.setInt(2, tipo);
      rs = sentencia.executeQuery();
      
      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        m.setCant(rs.getInt("cant"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos_totales.add(m);
      }
      
      for(Modelos m : modelos_totales){        
        int cant = m.getCant();
        Modelos mAct = null;
        for(Modelos mr : modelos_reservas){
          if(mr.getId()==m.getId() && mr.getTipo().getId()==m.getTipo().getId()){
            mAct = mr;
            break;
          }
        }
        if(mAct!=null){
          int cantR = mAct.getCant();
          if(cantR<cant)
            modelos_listado.add(m);
        } else
          modelos_listado.add(m);
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
    return modelos_listado;
  }

  public ArrayList<Modelos> getModelosDisponiblesXModelo2(int modelo) {
    ArrayList<Modelos> modelos = new ArrayList<Modelos>();
    PreparedStatement sentencia;
    ResultSet rs;
    String sql = "select m.* from modelos m inner join bicicletas b on b.id_modelo=m.id WHERE b.disponible=true AND b.baja=0 AND m.baja=0 AND m.id=? GROUP BY m.id";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, modelo);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      ConexionBD.getInstancia().CloseConn();
    }
    return modelos;
  }

  public ArrayList<Modelos> getModelosXTipo2(int tipo) {
    ArrayList<Modelos> modelos = new ArrayList<Modelos>();
    PreparedStatement sentencia;
    ResultSet rs;
    String sql = "select * from modelos WHERE id_tipo=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, tipo);
      rs = sentencia.executeQuery();

      while (rs.next()) {
        Modelos m = new Modelos();
        m.setId(rs.getInt("id"));
        m.setNombre(rs.getString("nombre"));
        m.setCaracteristicas_gral(rs.getString("descrip"));
        m.setPrecioXHr(rs.getDouble("precio_hr"));
        m.setPrecioXDia(rs.getDouble("precio_dia"));
        m.setUrl1(rs.getString("url1"));
        m.setUrl2(rs.getString("url2"));
        m.setUrl3(rs.getString("url3"));
        TiposBicicleta tb = new CatalogoTiposBicicletas().getTipo(rs.getInt("id_tipo"));
        m.setTipo(tb);
        modelos.add(m);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      ConexionBD.getInstancia().CloseConn();
    }
    return modelos;
  }
}
