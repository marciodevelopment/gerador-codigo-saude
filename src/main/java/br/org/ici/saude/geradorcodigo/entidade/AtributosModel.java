package br.org.ici.saude.geradorcodigo.entidade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AtributosModel {
  private final String nome;
  private final String mensagem;
  private final String tipo;
  private final List<AnotacaoModel> anotacoes;
  private MapeamentoModel mapeamento;
  private String nomeVariavel;



  public void setNomeVariavel(String nomeVariavel) {
    this.nomeVariavel = nomeVariavel;
  }

  public boolean temImport(String pacoteEntidade) {
    String tipoSemPacoteEntidade = tipo.replace(pacoteEntidade, "");
    return tipoSemPacoteEntidade.contains(".");
  }

  public Set<String> getImports(String pacote) {
    Set<String> imports = new HashSet<>();
    imports.add(GeradorImports.getImport(this.getTipo()));
    imports.addAll(this.getImportsEnum(pacote));
    imports.addAll(this.getImportsMapeamento());
    imports.addAll(this.anotacoes.stream().filter(an -> an.existeImport())
        .map(an -> an.getCodigoImport()).toList());
    return imports.stream().filter(imp -> imp != null && !imp.isBlank())
        .collect(Collectors.toSet());
  }

  private Set<String> getImportsMapeamento() {
    Set<String> imports = new HashSet<>();
    if (this.mapeamento == null)
      return imports;
    if (this.mapeamento.getExisteManyToOne().booleanValue())
      imports.add(GeradorImports.getImport("manytoone"));
    if (this.mapeamento.getExisteOneToMany().booleanValue())
      imports.add(GeradorImports.getImport("onetomany"));
    if (this.mapeamento.getExisteOneToOne().booleanValue())
      imports.add(GeradorImports.getImport("onetoone"));
    return imports;
  }

  private Set<String> getImportsEnum(String pacote) {
    Set<String> imports = new HashSet<>();
    if (isNotEnum()) {
      return imports;
    }
    imports.add(pacote + ".type." + tipo);
    imports.add(pacote + ".type." + tipo.replace("Type", "Converter"));
    return imports;
  }

  private boolean isNotEnum() {
    return !tipo.toLowerCase().contains("type");
  }

  public Boolean getExisteMapeamento() {
    return this.mapeamento != null;
  }

  public Boolean getTipoCollection() {
    return this.getExisteMapeamento() && this.mapeamento.getExisteOneToMany();
  }

  public String getTipo() {
    if (tipo.contains(".")) {
      return tipo.substring(tipo.lastIndexOf(".") + 1, tipo.length());
    }
    return tipo;
  }

  public AtributosModel addMapeamento(String tipoMapeamento, boolean joinColumn, String cascade) {
    boolean atributoPodeSerNulo = !atributoNaoPodeSerNulo();
    mapeamento = new MapeamentoModel(tipoMapeamento, joinColumn, cascade, atributoPodeSerNulo,
        this.getTipo(), nomeVariavel);
    return this;
  }

  public boolean atributoNaoPodeSerNulo() {
    return this.getAnotacoes().stream().filter(anotacao -> anotacao.naoNulo()).count() > 0;
  }

  public String getNomeGet() {
    return "get" + this.nome.substring(0, 1).toUpperCase()
        + this.nome.substring(1, this.nome.length()) + "()";
  }

  public Boolean getExisteAnotacaoGet() {
    return this.getAnotacoes().stream().filter(anotacao -> anotacao.anotacaoGet()).count() > 0;
  }


  public Boolean getAtributoDeveEstarNoConstrutor() {
    return this.getAnotacoes().stream().filter(anotacao -> anotacao.anotacaoDeveEstarNoConstrutor())
        .findFirst().isPresent();
  }



  public List<String> getAnotacoesConstrutor() {
    return this.anotacoes.stream().filter(AnotacaoModel::anotacaoDeveEstarNoConstrutor)
        .map(AnotacaoModel::anotacaoUsadaNoConstrutor).toList();
  }

  public void addAnotacoes(List<AnotacaoModel> anotacoes) {
    this.anotacoes.addAll(anotacoes);

  }



  /*
   * public boolean deveEstarNoConstrutor() { return
   * this.anotacoes.stream().anyMatch(this::anotacaoDeAtributoConstrutor); }
   * 
   */

  /*
   * 
   * 
   * 
   * 
   * 
   * 
   * public String getTipo() { if (tipo.contains(".")) { return tipo.substring(tipo.lastIndexOf(".")
   * + 1, tipo.length()); } return tipo; }
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   */
}
