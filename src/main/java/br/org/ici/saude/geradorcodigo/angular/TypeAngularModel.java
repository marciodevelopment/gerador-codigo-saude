package br.org.ici.saude.geradorcodigo.angular;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.entidade.ValorType;

public class TypeAngularModel extends BaseModel {
  private List<ValorType> valores = new ArrayList<>();

  public TypeAngularModel(String nome, String pacoteProjeto, String pacote,
      List<String> valoresStr) {
    super(nome, pacote, ArquivoAngularType.TYPE.getPacoteArquivo(pacoteProjeto, pacote));
    this.valores = valoresStr.stream().map(ValorType::of).toList();
  }

  public List<ValorType> getValores() {
    return valores;
  }


}
