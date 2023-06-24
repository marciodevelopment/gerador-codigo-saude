package br.org.ici.saude.geradorcodigo.angular;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RoutingModel extends BaseModel {
  private List<ModuleRoutingModel> rotas = new ArrayList<>();
  private String nomeRota;

  public RoutingModel(String pacote, String pacoteProjeto, List<ModuleRoutingModel> rotas,
      String nomeRota) {
    super(pacoteProjeto.replace(pacote + ".", ""), pacoteProjeto.replace(pacote + ".", ""),
        ArquivoAngularType.ROUTES.getPacoteArquivo(pacoteProjeto, pacote));

    this.rotas = rotas;
    this.nomeRota = nomeRota;
  }


}
