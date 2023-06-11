package br.org.ici.saude.geradorcodigo.configuracao;

import lombok.Getter;

@Getter
public class Mapeamento {
  private String tipoMapeamento;
  private boolean joinColumn;
  private String cascade;

  public boolean isCascade() {
    return this.cascade != null && !this.cascade.isBlank();
  }
}
