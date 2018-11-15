package Datos;

import java.sql.Connection; //para crear la conexion
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

  //creamos una variable de la instancia de la DB
  private static ConexionBD instancia;

  //metodo para obtener la unica instancia
  public static ConexionBD getInstancia() {
    if (instancia == null) {
      instancia = new ConexionBD();
    }
    return instancia;
  }
  // DATOS USER

  //private static String DBURL="jdbc:mysql://node101493-bookstore.jelasticlw.com.br/bookstore";
  //private static String DBUSER="root";
  //private static String DBPASSWORD="TLYvon47605";
  /*String URL =  "jdbc:mysql://mariadb{node_id}-{environment_name}.{hoster_domain}/{dbname}";
DriverManager.getConnection(URL, user_name,user_password);*/
  
  
  //private static final String DBURL = "jdbc:mysql://localhost:3306/bici_amiga";
	//https://node23970-biciamiga.jelastic.saveincloud.net/bici_amiga
  private static final String DBURL = "jdbc:mysql://node24319-biciamigajava.jelastic.saveincloud.net";
  private static final String DBUSER = "root";
  //private static final String DBPASSWORD = "";
  private static final String DBPASSWORD = "SMRpym93614";

  private ConexionBD(){}
  
  //CREAMOS UNA VARIABLE DE TIPO CONECCION
  private Connection conn;
  //hacemos la coneccion

  public Connection getconn() {

    try {
      //validar si es null la connection, de ser asi abrirla
      if (conn == null || !conn.isValid(3)) {
        //cargar en memoria el driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        //conectamos con la base de datos
        /*crear un objeto de la clase Connection, para esto la clase DriverManager 
				tiene un m�todo llamado getConnection que retorna un objeto de la clase Connection*/
        conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
      }

    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
  //cerrar la conexion

  public void CloseConn() {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
