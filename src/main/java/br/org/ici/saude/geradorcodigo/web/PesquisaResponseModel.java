package br.org.ici.saude.geradorcodigo.web;

import java.util.Collection;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;

public class PesquisaResponseModel extends BaseModel {

  public PesquisaResponseModel(String nome, String pacote, Collection<AtributosModel> atributos) {
    super(nome, pacote, ArquivoJavaType.PESQUISA_RESPONSE.getPacoteArquivo(pacote));
    super.addAtributos(atributos);
  }

}
