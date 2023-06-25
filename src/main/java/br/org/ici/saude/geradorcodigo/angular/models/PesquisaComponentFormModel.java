package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.Collection;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;

public class PesquisaComponentFormModel extends BaseAngularModel {
  public PesquisaComponentFormModel(String nome, String pacoteProjeto, String pacote,
      Collection<? extends AtributosModel> atributos, String mensagem, boolean existeNovo,
      boolean existeEdit, boolean existeDelete) {
    super(nome.toLowerCase(),
        ArquivoAngularType.PESQUISA_F0RM_COMPONENT.caminhoArquivo(pacoteProjeto, pacote) + "/"
            + nome.toLowerCase() + "-pesquisa-form/");
    super.addAtributos(atributos);
  }

}
