package br.org.ici.saude.geradorcodigo.entidade;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;



@Getter
public class EntidadeModel extends BaseModel {

  public EntidadeModel(String nome, String pacote) {
    super(nome, pacote);
  }

  public Boolean getExisteConstrutor() {
    return !this.getExisteBuilderConstrutor();
  }

  public List<AtributosModel> getAtributosConstrutor() {
    return this.getAtributos().stream().filter(AtributosModel::getAtributoDeveEstarNoConstrutor)
        .toList();
  }


  public Boolean getExisteBuilderConstrutor() {
    return this.getAtributosConstrutor().size() > 7;
  }

}
