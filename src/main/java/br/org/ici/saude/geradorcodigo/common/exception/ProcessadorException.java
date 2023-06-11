package br.org.ici.saude.geradorcodigo.common.exception;

public class ProcessadorException extends RuntimeException {

  public ProcessadorException(Exception e) {
    super(e);
  }

  private static final long serialVersionUID = 6657186435660373862L;

}
