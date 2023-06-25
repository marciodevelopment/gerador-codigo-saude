package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import br.org.ici.saude.geradorcodigo.entidade.ValorType;

public class TypeAngularModel extends BaseAngularModel {
  private List<ValorType> valores = new ArrayList<>();

  public TypeAngularModel(String nome, String pacote, String pacoteProjeto,
      List<String> valoresStr) {
    super(nome, ArquivoAngularType.TYPE.caminhoArquivo(pacoteProjeto, pacote));
    this.valores = valoresStr.stream().map(ValorType::of).toList();
  }

  public List<ValorType> getValores() {
    return valores;
  }


}
