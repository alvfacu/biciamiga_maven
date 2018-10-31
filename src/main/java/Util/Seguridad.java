package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Seguridad {

  public String md5(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());
    byte[] digest = md.digest();
    String encriptado = DatatypeConverter.printHexBinary(digest).toUpperCase();
    return encriptado;
  }
}
