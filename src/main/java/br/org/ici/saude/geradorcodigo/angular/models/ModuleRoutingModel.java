package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.AtributosAngularModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(of = "nomeComponent")
@RequiredArgsConstructor
@ToString
@Getter
public class ModuleRoutingModel {
  private final String nomeComponent;
  private final String nomeModulo;
  private final Boolean existePesquisa;
  private final Boolean existeAtualizar;
  private final Boolean existeSalvar;
  private final String path;
  private final String nomeRota;

  private final String pacoteEntidade;
  @Setter
  private List<AtributosAngularModel> atributosGeradoresDeImport = new ArrayList<>();

  public Boolean getExisteForm() {
    return this.existeAtualizar || this.existeSalvar;
  }


  public String getNomeVariavel() {
    return this.nomeComponent.toLowerCase();
  }

}
