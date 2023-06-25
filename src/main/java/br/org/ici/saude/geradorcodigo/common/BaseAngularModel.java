package br.org.ici.saude.geradorcodigo.common;

import lombok.Getter;


@Getter
public class BaseAngularModel extends BaseModel implements ArquivoAngularModel {
  @Getter
  private String caminhoArquivo;


  public BaseAngularModel(String nome, String caminhoArquivo) {
    super(nome);
    this.caminhoArquivo = caminhoArquivo;
  }

  public String getNomeComponent() {
    return super.getNome().substring(0, 1).toUpperCase()
        + super.getNome().substring(1, super.getNome().length());
  }

}
