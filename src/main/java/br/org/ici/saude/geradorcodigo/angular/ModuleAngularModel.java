package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModuleAngularModel extends BaseModel {
  private String routesName;
  private String nomeModule;
  private Boolean existePesquisa;
  private Boolean existeForm;
  private String nomeComponente;

  public ModuleAngularModel(String pacote, String pacoteProjeto, List<String> metodos) {
    super(pacoteProjeto.replace(pacote + ".", ""), pacoteProjeto.replace(pacote + ".", ""),
        ArquivoAngularType.MODULE.getPacoteArquivo(pacoteProjeto, pacote));
    this.nomeModule = pacoteProjeto.replace(pacote + ".", "");
    this.routesName = nomeModule.toUpperCase() + "_ROUTES";

    this.existePesquisa = metodos.toString().toLowerCase().contains("pesquisa");
    this.existeForm = metodos.toString().toLowerCase().contains("post");
    this.nomeComponente = super.getNome().substring(0, 1).toUpperCase()
        + super.getNome().substring(1, super.getNome().length());
  }
}
