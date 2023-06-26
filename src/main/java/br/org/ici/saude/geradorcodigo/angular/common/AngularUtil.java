package br.org.ici.saude.geradorcodigo.angular.common;

public class AngularUtil {
  public static String extrairNomeVariavelModulo(String pacoteEntidade) {
    return pacoteEntidade.substring(pacoteEntidade.lastIndexOf(".") + 1, pacoteEntidade.length());
  }

  public static String extrairNomeModulo(String pacoteEntidade) {
    String nomeModulo =
        pacoteEntidade.substring(pacoteEntidade.lastIndexOf(".") + 1, pacoteEntidade.length());
    return nomeModulo.substring(0, 1).toUpperCase() + nomeModulo.substring(1, nomeModulo.length());
  }
}
