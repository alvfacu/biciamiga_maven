package Entidades;

public class Usuarios {
  
  private int id;
  private String apynom, usuario, contrasenia, email, domicilio, telefono, documento;
  private boolean adm, habilitado, mecanico;

  public boolean isMecanico() {
    return mecanico;
  }

  public void setMecanico(boolean mecanico) {
    this.mecanico = mecanico;
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getApynom() {
    return apynom;
  }

  public void setApynom(String apynom) {
    this.apynom = apynom;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public boolean isAdm() {
    return adm;
  }

  public void setAdm(boolean adm) {
    this.adm = adm;
  }

  public boolean isHabilitado() {
    return habilitado;
  }

  public void setHabilitado(boolean habilitado) {
    this.habilitado = habilitado;
  }

  public Usuarios() {
  }

  public Usuarios(String apynom, String usuario, String contrasenia, String email, String domicilio, String telefono, String documento, boolean adm, boolean habilitado, boolean mecanico) {
    this.apynom = apynom;
    this.usuario = usuario;
    this.contrasenia = contrasenia;
    this.email = email;
    this.domicilio = domicilio;
    this.telefono = telefono;
    this.documento = documento;
    this.adm = adm;
    this.habilitado = habilitado;
    this.mecanico = mecanico;
  }

  public Usuarios(int id, String apynom, String usuario, String contrasenia, String email, String domicilio, String telefono, String documento, boolean adm, boolean habilitado, boolean mecanico) {
    this.id = id;
    this.apynom = apynom;
    this.usuario = usuario;
    this.contrasenia = contrasenia;
    this.email = email;
    this.domicilio = domicilio;
    this.telefono = telefono;
    this.documento = documento;
    this.adm = adm;
    this.habilitado = habilitado;
    this.mecanico = mecanico;
  }
  
  
  
}
