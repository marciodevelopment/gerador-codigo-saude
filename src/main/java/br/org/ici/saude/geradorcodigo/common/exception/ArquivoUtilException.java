package br.org.ici.saude.geradorcodigo.common.exception;

import java.io.IOException;

public class ArquivoUtilException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ArquivoUtilException(IOException e) {
    super(e);
  }


}
