package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.org.ici.saude.geradorcodigo.angular.AtributosAngularModel;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModuleAngularModel extends BaseAngularModel {
  private String routesName;
  private String nomeModule;
  private Boolean existePesquisa;
  private Boolean existeForm;
  private String nomeComponente;
  @Getter
  private Set<ModuleRoutingModel> rotas = new HashSet<>();

  public ModuleAngularModel(String pacoteProjeto, String pacote, List<ModuleRoutingModel> rotas) {
    super(pacote.replace(pacoteProjeto + ".", ""),
        ArquivoAngularType.MODULE.caminhoArquivo(pacoteProjeto, pacote));
    this.rotas.addAll(rotas);
    this.nomeModule = pacote.replace(pacoteProjeto + ".", "");
    this.routesName = nomeModule.toUpperCase() + "_ROUTES";
    this.nomeComponente = super.getNome().substring(0, 1).toUpperCase()
        + super.getNome().substring(1, super.getNome().length());
  }

  public Set<AtributosAngularModel> getAtributosImports() {
    Set<AtributosAngularModel> atributos = new HashSet<>();
    List<ModuleRoutingModel> rotasComAtributosImport = this.rotas.stream()
        .filter(rota -> !rota.getAtributosGeradoresDeImport().isEmpty()).toList();
    rotasComAtributosImport.forEach(rota -> atributos.addAll(rota.getAtributosGeradoresDeImport()));
    return atributos;
  }

}
