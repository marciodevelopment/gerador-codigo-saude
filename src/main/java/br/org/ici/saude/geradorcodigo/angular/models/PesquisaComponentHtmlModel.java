package br.org.ici.saude.geradorcodigo.angular.models;

import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;

public class PesquisaComponentHtmlModel extends BaseAngularModel {
  public PesquisaComponentHtmlModel(String nome, String pacoteProjeto, String pacote) {
    super(nome.toLowerCase(),
        ArquivoAngularType.PESQUISA_COMPONENT_HTML.caminhoArquivo(pacoteProjeto, pacote,
            nome.toLowerCase()) + "/" + nome.toLowerCase() + "-pesquisa/");
  }

}
