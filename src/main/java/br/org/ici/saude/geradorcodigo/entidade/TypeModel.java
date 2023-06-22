package br.org.ici.saude.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;

public class TypeModel extends BaseModel {
  private List<ValorType> valores = new ArrayList<>();

  public TypeModel(String nome, String pacote, List<String> valoresStr) {
    super(nome, pacote, ArquivoJavaType.TYPE.getPacoteArquivo(pacote));
    this.valores = valoresStr.stream().map(ValorType::of).toList();
  }

  public List<ValorType> getValores() {
    return valores;
  }


}
