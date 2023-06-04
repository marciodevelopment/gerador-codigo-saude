package br.org.ici.saude.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import br.org.ici.saude.geradorcodigo.common.BaseModel;

public class TypeModel extends BaseModel {
  private List<ValorType> valores = new ArrayList<>();

  public TypeModel(String nome, String pacote) {
    super(nome, pacote);
  }

  public void addValores(String[] valoresString) {
    this.valores = Stream.of(valoresString).map(ValorType::of).collect(Collectors.toList());
  }

  public List<ValorType> getValores() {
    return valores;
  }


}
