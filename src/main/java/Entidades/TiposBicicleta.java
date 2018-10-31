package Entidades;

public class TiposBicicleta {
  
  private int id;
  private String nombre;
  private String descripcion;
  private String url;

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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  public TiposBicicleta() {
  }

  public TiposBicicleta(int id, String nombre, String descripcion, String url) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.url = url;
  }

  public TiposBicicleta(String nombre, String descripcion, String url) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.url = url;
  }
  
}
