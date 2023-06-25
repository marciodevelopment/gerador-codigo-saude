package br.org.ici.saude.geradorcodigo.configuracao;

import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
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


  public ArquivoFonte(ArquivoModel arqModel, ArquivoJavaType arquivoType, String arquivo) {
    this(arquivoType.getNomeArquivo(arqModel.getNome()),
        arquivoType.getPacoteArquivo(arqModel.getPacote()), arquivo);
  }


  public ArquivoFonte(ArquivoAngularModel arqModel, ArquivoAngularType arquivoType, String arquivo,
      String caminhoArquivo) {
    this(arquivoType.getNomeArquivo(arqModel.getNome()), caminhoArquivo, arquivo);
  }


  public boolean isTest() {
    return this.nomeArquivo.toLowerCase().contains("test");
  }

}
