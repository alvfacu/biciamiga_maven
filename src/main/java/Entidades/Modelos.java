package Entidades;

public class Modelos {
  
  private int id;
  private String nombre;
  private String caracteristicas_gral;
  private double precioXHr;
  private double precioXDia;
  private String url1;
  private String url2;
  private String url3;
  private TiposBicicleta tipo;
  private int cant;

  public int getCant() {
    return cant;
  }

  public void setCant(int cant) {
    this.cant = cant;
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCaracteristicas_gral() {
    return caracteristicas_gral;
  }

  public void setCaracteristicas_gral(String caracteristicas_gral) {
    this.caracteristicas_gral = caracteristicas_gral;
  }

  public double getPrecioXHr() {
    return precioXHr;
  }

  public void setPrecioXHr(double precioXHr) {
    this.precioXHr = precioXHr;
  }

  public double getPrecioXDia() {
    return precioXDia;
  }

  public void setPrecioXDia(double precioXDia) {
    this.precioXDia = precioXDia;
  }

  public String getUrl1() {
    return url1;
  }

  public void setUrl1(String url1) {
    this.url1 = url1;
  }

  public String getUrl2() {
    return url2;
  }

  public void setUrl2(String url2) {
    this.url2 = url2;
  }

  public String getUrl3() {
    return url3;
  }

  public void setUrl3(String url3) {
    this.url3 = url3;
  }


  public TiposBicicleta getTipo() {
    return tipo;
  }

  public void setTipo(TiposBicicleta tipo) {
    this.tipo = tipo;
  }

  public Modelos(int id, String nombre, String caracteristicas_gral, double precioXHr, double precioXDia, String url1, String url2, String url3 , TiposBicicleta tipo) {
    this.id = id;
    this.nombre = nombre;
    this.caracteristicas_gral = caracteristicas_gral;
    this.precioXHr = precioXHr;
    this.precioXDia = precioXDia;
    this.url1 = url1;
    this.url2 = url2;
    this.url3 = url3;
    this.tipo = tipo;
  }

  public Modelos() {
  }

  public Modelos(String nombre, String caracteristicas_gral, double precioXHr, double precioXDia, String url1, String url2, String url3, TiposBicicleta tipo) {
    this.nombre = nombre;
    this.caracteristicas_gral = caracteristicas_gral;
    this.precioXHr = precioXHr;
    this.precioXDia = precioXDia;
    this.url1 = url1;
    this.url2 = url2;
    this.url3 = url3;
    this.tipo = tipo;
  }
  
}
