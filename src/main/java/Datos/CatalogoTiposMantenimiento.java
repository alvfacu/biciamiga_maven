package Datos;

import Entidades.Mantenimientos;
import Entidades.TiposMantenimiento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CatalogoTiposMantenimiento {
  
  public ArrayList<TiposMantenimiento> getTipos() {
    ArrayList<TiposMantenimiento> tipos = new ArrayList<TiposMantenimiento>();
    Statement sentencia = null;
    ResultSet rs = null;
    String sql = "select * from tipos_mantenimiento where baja=0 order by nombre";
    try {
      sentencia = ConexionBD.getInstancia().getconn().createStatement();
      rs = sentencia.executeQuery(sql);

      while (rs.next()) {
        TiposMantenimiento tm = new TiposMantenimiento();
        tm.setId(rs.getInt("id"));
        tm.setDescripcion(rs.getString("descripcion"));
        tm.setKmParaMantenimiento(rs.getDouble("km"));
        tm.setObligatorio(rs.getBoolean("obligatorio"));
        tm.setNombre(rs.getString("nombre"));
        tipos.add(tm);
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
    return tipos;
  }

  public TiposMantenimiento getTipo(int id) {    
    PreparedStatement sentencia;
    ResultSet rs;
    TiposMantenimiento tm = null;
    String sql = "select * from tipos_mantenimiento where id=?";
    
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        tm = new TiposMantenimiento();
        tm.setDescripcion(rs.getString("descripcion"));
        tm.setKmParaMantenimiento(rs.getDouble("km"));
        tm.setObligatorio(rs.getBoolean("obligatorio"));
        tm.setNombre(rs.getString("nombre"));
        tm.setId(rs.getInt("id"));
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return tm;
  }

  public void altaTipoMantenimiento(TiposMantenimiento tm) {
    PreparedStatement sentencia = null;
    ResultSet rs;
    String sql = "insert into tipos_mantenimiento(descripcion,km,obligatorio,nombre) "
            + "values(?,?,?,?)";
    try {
      sentencia=ConexionBD.getInstancia().getconn().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
      sentencia.setString(1,tm.getDescripcion());
      sentencia.setDouble(2,tm.getKmParaMantenimiento());
      sentencia.setBoolean(3,tm.isObligatorio());
      sentencia.setString(4,tm.getNombre());
      sentencia.execute();
      rs=sentencia.getGeneratedKeys();
      if(rs!=null && rs.next()){
        tm.setId(rs.getInt(1));
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

  public void bajaTipoMantenimiento(TiposMantenimiento tm) {
    PreparedStatement sentencia = null;
    //String sql = "delete from tipos_mantenimiento where id=?";
    String sql = "update tipos_mantenimiento set baja=1 where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, tm.getId());
      sentencia.execute();
      
      sql = "update detalle_mantenimiento set completado=1 where id_tipom=?";
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setInt(1, tm.getId());
      sentencia.execute();
      
      ArrayList<Integer> id_detalles = new ArrayList<Integer>();
      PreparedStatement sentencia1 = null;
      ResultSet rs,rs2 = null;
      
      sql = "select * from detalle_mantenimiento where id_tipom=?";
      sentencia1 = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia1.setInt(1, tm.getId());
      rs = sentencia1.executeQuery();

      while (rs.next()) 
        id_detalles.add(rs.getInt("id"));
      
      for(Integer id : id_detalles){
        sql = "select count(*) as cant from detalle_mantenimiento where id=?";
        sentencia1 = ConexionBD.getInstancia().getconn().prepareStatement(sql);
        sentencia1.setInt(1, id);
        rs2 = sentencia1.executeQuery(); 
        
        if (rs2.next())
        {
          if(rs2.getInt("cant")==1)
          {
            Mantenimientos m = new CatalogoMantenimientos().getMantenimiento(id);
            if(m.getFechaEgreso()==null)
              new CatalogoMantenimientos().bajaMantenimientosXTipoMantenimiento(m.getId());
          }
        }
        
      }
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

  public void modificarTipoMantenimiento(TiposMantenimiento tm) {
    PreparedStatement sentencia = null;
    String sql = "update tipos_mantenimiento set descripcion=?, km=?, obligatorio=?, nombre=?"
            + " where id=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, tm.getDescripcion());
      sentencia.setDouble(2, tm.getKmParaMantenimiento());
      sentencia.setBoolean(3, tm.isObligatorio());
      sentencia.setString(4, tm.getNombre());
      sentencia.setInt(5, tm.getId());
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

  public int existenMantenimientosActivosXTipo(String id) {
    PreparedStatement sentencia;
    ResultSet rs;
    
    String sql = "select count(*) from detalle_mantenimiento where id_tipom=? and completado=false";
    int cont = 0;
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setString(1, id);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        cont = rs.getInt(1);     
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    return cont;
  }

  public boolean tieneMantenimientosObligatorios(double km) {
    PreparedStatement sentencia;
    ResultSet rs;
    boolean estado = true;
    String sql = "select count(*) from tipos_mantenimiento where obligatorio=true and baja=0 and km<=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setDouble(1, km);
      rs = sentencia.executeQuery();
      
      if (rs.next()) {
        estado = rs.getInt(1)>0;
      }

    } catch (SQLException sqle) {
      sqle.printStackTrace();
      estado = false;
    }
    return estado;
  }

  public ArrayList<TiposMantenimiento> getMantenimientosObligatorios(double km) {
    PreparedStatement sentencia;
    ResultSet rs;
    ArrayList<TiposMantenimiento> obligatorios = new ArrayList<TiposMantenimiento>();
    String sql = "select * from tipos_mantenimiento where obligatorio=true and baja=0 and km<=?";
    try {
      sentencia = ConexionBD.getInstancia().getconn().prepareStatement(sql);
      sentencia.setDouble(1, km);
      rs = sentencia.executeQuery();
      
      while (rs.next()) {
        TiposMantenimiento tm = new TiposMantenimiento();
        tm.setId(rs.getInt("id"));
        tm.setDescripcion(rs.getString("descripcion"));
        tm.setKmParaMantenimiento(rs.getDouble("km"));
        tm.setObligatorio(rs.getBoolean("obligatorio"));
        tm.setNombre(rs.getString("nombre"));
        obligatorios.add(tm);
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    } finally {
      ConexionBD.getInstancia().CloseConn();
    }
    return obligatorios;
  }
}
