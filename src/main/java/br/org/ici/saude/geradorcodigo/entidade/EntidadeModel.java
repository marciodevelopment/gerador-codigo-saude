package br.org.ici.saude.geradorcodigo.entidade;

import java.util.List;
import java.util.Set;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;



@Getter
public class EntidadeModel extends BaseModel {

  public EntidadeModel(String nome, String pacote) {
    super(nome, pacote, ArquivoJavaType.ENTITY.getPacoteArquivo(pacote));
  }

  public Boolean getExisteConstrutor() {
    return !this.getExisteBuilderConstrutor();
  }

  public List<AtributosModel> getAtributosConstrutor() {
    return this.getAtributos().stream().filter(AtributosModel::getAtributoDeveEstarNoConstrutor)
        .toList();
  }


  public List<AtributosModel> getAtributosUpdate() {
    return this.getAtributos().stream().filter(AtributosModel::getAtributoDeveEstarNaAtualizacao)
        .toList();
  }

  public Boolean getExisteBuilderConstrutor() {
    return this.getAtributosConstrutor().size() > 7;
  }

  public List<AtributosModel> getAtributosView() {
    return this.getAtributos().stream().map(atr -> {
      if (atr.getExisteMapeamento().booleanValue()) {
        return new AtributosModel(
            "cd" + atr.getNome().substring(0, 1).toUpperCase()
                + atr.getNome().substring(1, atr.getNome().length()),
            atr.getMensagem(), "Integer", null);
      }
      return atr;
    }).toList();
  }

  @Override
  public void setImports(Set<String> imports) {
    super.setImports(imports);
  }

}
