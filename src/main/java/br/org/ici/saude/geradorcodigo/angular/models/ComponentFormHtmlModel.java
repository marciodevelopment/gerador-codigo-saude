package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import lombok.Getter;

public class ComponentFormHtmlModel extends BaseAngularModel {
  @Getter
  private String mensagem;

  public ComponentFormHtmlModel(String nome, String pacoteProjeto, String pacote,
      Collection<? extends AtributosModel> atributos, String mensagem) {
    super(nome.toLowerCase(),
        ArquivoAngularType.F0RM_COMPONENT.caminhoArquivo(pacoteProjeto, pacote) + "/"
            + nome.toLowerCase() + "-form/");
    super.addAtributos(atributos.stream().filter(atr -> !atr.isId(nome)).toList());
    this.mensagem = mensagem;

  }

  public List<AtributosModel> getTypes() {
    return super.getAtributos().stream().filter(atr -> atr.getTipo().toLowerCase().contains("type"))
        .toList();
  }

  public Boolean getExisteType() {
    return !this.getTypes().isEmpty();
  }
}
