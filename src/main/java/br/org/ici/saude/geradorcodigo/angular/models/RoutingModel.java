package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RoutingModel extends BaseAngularModel {
  private Set<ModuleRoutingModel> rotas = new HashSet<>();
  private String nomeRota;

  public RoutingModel(String pacoteProjeto, String pacote, List<ModuleRoutingModel> rotas,
      String nomeRota) {
    super(pacote.replace(pacoteProjeto + ".", ""),
        ArquivoAngularType.MODULE.caminhoArquivo(pacoteProjeto, pacote));
    this.rotas.addAll(rotas);
    this.nomeRota = nomeRota;
  }


}
