package br.org.ici.saude.geradorcodigo.configuracao;

import lombok.Getter;

@Getter
public class ArquivoFonte {
  private String caminho;
  private String arquivo;
  private String nomeArquivo;


  public ArquivoFonte(String nomeArquivo, String caminho, String arquivo) {
    super();
    this.nomeArquivo = nomeArquivo;
    this.caminho = caminho;
    this.arquivo = arquivo;
  }
}
