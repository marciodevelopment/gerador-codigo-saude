package br.org.ici.saude.geradorcodigo.web;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;

public class PesquisaResponseModel extends BaseModel {

  public PesquisaResponseModel(String nome, String pacote, List<AtributosModel> atributos) {
    super(nome, pacote);
    super.addAtributos(atributos);
  }

}
