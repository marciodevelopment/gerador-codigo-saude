package br.org.ici.saude.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class AtributosModel {
  private final String nome;
  private final String mensagem;
  private final String tipo;
  private final List<AnotacaoModel> anotacoes;
  private MapeamentoModel mapeamento;
  private String nomeVariavel;
  private boolean semCascadeDesnomalizado = false;
  private String tipoOrigem;
  @Setter
  private boolean editavel;

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
    imports.addAll(this.getAnotacoes().stream().filter(an -> an.existeImport())
        .map(an -> an.getCodigoImport()).toList());
    return imports.stream().filter(imp -> imp != null && !imp.isBlank())
        .collect(Collectors.toSet());
  }

  private Set<String> getImportsMapeamento() {
    Set<String> imports = new HashSet<>();
    if (this.mapeamento == null)
      return new HashSet<>();

    if (this.tipo.toLowerCase().contains(".") && this.tipo.toLowerCase().contains("entity")) {
      imports.add(tipo);
    }

    imports.addAll(this.mapeamento.getImports());
    return imports;
  }

  private Set<String> getImportsEnum(String pacote) {
    Set<String> imports = new HashSet<>();
    if (isNotEnum()) {
      return imports;
    }
    imports.add(pacote + ".entity.type." + tipo);
    imports.add(pacote + ".entity.type." + tipo.replace("Type", "Converter"));
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
    return this.getAnotacoes().stream().anyMatch(AnotacaoModel::naoNulo);
  }

  public String getNomeGet() {
    return "get" + this.nome.substring(0, 1).toUpperCase()
        + this.nome.substring(1, this.nome.length()) + "()";
  }

  public String getNomeSet() {
    return "set" + this.nome.substring(0, 1).toUpperCase()
        + this.nome.substring(1, this.nome.length());
  }

  public Boolean getExisteAnotacaoGet() {
    return this.getAnotacoes().stream().anyMatch(AnotacaoModel::anotacaoGet);
  }


  public Boolean getAtributoDeveEstarNoConstrutor() {
    return this.getAnotacoes().stream().anyMatch(AnotacaoModel::anotacaoDeveEstarNoConstrutor);
  }


  public Boolean getAtributoDeveEstarNaAtualizacao() {
    return this.editavel
        && this.getAnotacoes().stream().anyMatch(AnotacaoModel::anotacaoDeveEstarNoConstrutor);
  }

  public List<String> getAnotacoesConstrutor() {
    return this.anotacoes.stream().filter(AnotacaoModel::anotacaoDeveEstarNoConstrutor)
        .map(AnotacaoModel::anotacaoUsadaNoConstrutor).toList();
  }

  public void addAnotacoes(List<AnotacaoModel> anotacoes) {
    this.anotacoes.addAll(anotacoes);

  }

  public void setAnatcoes(List<AnotacaoModel> anotacoes) {
    this.anotacoes.clear();
    this.anotacoes.addAll(anotacoes);
  }

  public void addAnotacao(AnotacaoModel anotacao) {
    this.anotacoes.add(anotacao);

  }

  public List<AnotacaoModel> getAnotacoes() {
    if (this.anotacoes == null)
      return new ArrayList<>();
    return new ArrayList<>(this.anotacoes);
  }

  public boolean isId(String nomeClasse) {
    return this.nome.equalsIgnoreCase("cd" + nomeClasse);
  }

  public boolean isNotNull() {
    return this.anotacoes.stream().anyMatch(AnotacaoModel::isNotNull);
  }

  public boolean isNotBlank() {
    return this.anotacoes.stream().anyMatch(AnotacaoModel::naoNulo);
  }

  public boolean getRequired() {
    return this.anotacoes.stream().anyMatch(AnotacaoModel::naoNulo);
  }

  public boolean getExisteAnotacaoConverter() {
    return this.anotacoes.stream().anyMatch(AnotacaoModel::isConverter);

  }

  public Boolean getTipoData() {
    return this.tipo.toLowerCase().contains("date");
  }


  public void setSemCascadeDesnomalizado(boolean semCascadeDesnomalizado) {
    this.semCascadeDesnomalizado = semCascadeDesnomalizado;
  }

  public Boolean getSemCascadeDesnomalizado() {
    return semCascadeDesnomalizado;
  }

  public void setTipoOrigem(String tipoOrigem) {
    this.tipoOrigem = tipoOrigem;
  }

  public String getTipoOrigem() {
    return tipoOrigem;
  }

  public String getTipoOrigemComponente() {
    String tipoOrigemComponente = this.tipoOrigem.replace("Entity", "");
    tipoOrigemComponente = tipoOrigemComponente.substring(tipoOrigemComponente.lastIndexOf(".") + 1,
        tipoOrigemComponente.length());
    return tipoOrigemComponente.substring(0, 1).toLowerCase()
        + tipoOrigemComponente.substring(1, tipoOrigemComponente.length());
  }
}
