package br.org.ici.saude.geradorcodigo.angular;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import lombok.Getter;

public class InterfaceAngularModel extends BaseModel {

  @Getter
  private String sufixo;

  public InterfaceAngularModel(ArquivoAngularType arquivoAngular, String nome, String pacoteProjeto,
      String pacote, Collection<? extends AtributosModel> atributos) {
    super(nome, pacote, arquivoAngular.getPacoteArquivo(pacoteProjeto, pacote));
    super.addAtributos(atributos);
    this.sufixo = arquivoAngular.getSufixo();
  }

  @Override
  public Set<String> getImports() {
    return super.getImports().stream().filter(imp -> imp.contains("Type"))
        .map(imp -> imp.substring(imp.lastIndexOf(".") + 1, imp.length()))
        .collect(Collectors.toSet());
  }

}
