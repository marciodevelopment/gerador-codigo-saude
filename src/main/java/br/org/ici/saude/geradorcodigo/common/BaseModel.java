package br.org.ici.saude.geradorcodigo.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BaseModel implements ArquivoModel {
  private final String nome;
  private final String pacote;
  private Set<String> imports = new HashSet<>();
  private List<AtributosModel> atributos = new ArrayList<>();

  public BaseModel addImport(String nomeImport) {
    this.imports.add(nomeImport);
    return this;
  }

  public BaseModel addImports(List<String> nomeImports) {
    this.imports.addAll(nomeImports);
    return this;
  }

  public String getNomeVariavel() {
    return this.nome.substring(0, 1).toLowerCase() + this.nome.substring(1, this.nome.length());
  }

  public BaseModel addAtributos(Collection<AtributosModel> atributos) {
    this.atributos.addAll(atributos);
    this.atributos.stream().forEach(atr -> this.imports.addAll(atr.getImports(pacote)));
    return this;
  }

  public BaseModel addAtributos(List<AtributosModel> atributos) {
    this.atributos.addAll(atributos);
    this.atributos.stream().forEach(atr -> this.imports.addAll(atr.getImports(pacote)));
    return this;
  }

}
