package br.org.ici.saude.geradorcodigo.common;

public enum PadraNomes {
  ENTITY("Entity");

  private String sufixo;

  PadraNomes(String sufixo) {
    this.sufixo = sufixo;
  }

  public String getSufixo() {
    return sufixo;
  }



}
