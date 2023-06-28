package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import lombok.Getter;

public class PesquisaComponentModel extends BaseAngularModel {
  @Getter
  private Boolean existeDelete;
  @Getter
  private Boolean existeEdit;
  @Getter
  private Boolean existeNovo;
  @Getter
  private String mensagem;
  @Getter
  private String nomeComponent;

  public PesquisaComponentModel(String nome, String pacoteProjeto, String pacote,
      Collection<? extends AtributosModel> atributos, String mensagem, boolean existeNovo,
      boolean existeEdit, boolean existeDelete) {
    super(nome.toLowerCase(), ArquivoAngularType.PESQUISA_COMPONENT.caminhoArquivo(pacoteProjeto,
        pacote, nome.toLowerCase()) + "/" + nome.toLowerCase() + "-pesquisa/");
    super.addAtributos(atributos);
    this.existeDelete = existeDelete;
    this.existeEdit = existeEdit;
    this.existeNovo = existeNovo;
    this.mensagem = mensagem;
    this.nomeComponent = nome;
  }

  @Override
  public Set<String> getImports() {
    return super.getImports().stream().filter(imp -> imp.contains("Type"))
        .map(imp -> imp.substring(imp.lastIndexOf(".") + 1, imp.length()))
        .collect(Collectors.toSet());
  }



}
