package br.org.ici.saude.geradorcodigo.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import lombok.Getter;


@Getter
public class BaseModel implements ArquivoModel {
  private final String nome;
  private final String pacote;

  private final String pacoteArquivo;
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

  public BaseModel addAtributos(Collection<? extends AtributosModel> atributos) {
    this.atributos.addAll(atributos);
    this.atributos.stream().forEach(atr -> this.imports.addAll(atr.getImports(pacote)));
    return this;
  }

  public BaseModel addAtributos(List<AtributosModel> atributos) {
    this.atributos.addAll(atributos);
    this.atributos.stream().forEach(atr -> this.imports.addAll(atr.getImports(pacote)));
    return this;
  }

  public void setImports(Set<String> imports) {
    this.imports = imports;
  }

  public BaseModel(String nome, String pacote, String pacoteArquivo) {
    super();
    this.nome = nome;
    this.pacote = pacote;
    this.pacoteArquivo =
        pacoteArquivo.substring(1, pacoteArquivo.length() - 1).replaceAll("/", ".");
  }

}
