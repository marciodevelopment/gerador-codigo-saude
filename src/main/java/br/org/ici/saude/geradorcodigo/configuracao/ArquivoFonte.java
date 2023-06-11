package br.org.ici.saude.geradorcodigo.configuracao;

import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
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


  public ArquivoFonte(ArquivoModel arqModel, ArquivoType arquivoType, String arquivo) {
    this(arquivoType.getNomeArquivo(arqModel.getNome()),
        arquivoType.getCaminhoArquivo(arqModel.getPacote()), arquivo);
  }

}
