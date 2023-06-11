package br.org.ici.saude.geradorcodigo.web;

import java.util.Collection;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;

public class GetResponse extends BaseModel {
  public GetResponse(String nome, String pacote, Collection<AtributosModel> atributos) {
    super(nome, pacote, ArquivoType.GET_RESPONSE.getPacoteArquivo(pacote));
    super.addAtributos(atributos);
  }
}
