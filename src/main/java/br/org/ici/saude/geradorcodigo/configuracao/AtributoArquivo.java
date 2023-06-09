package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.entidade.AnotacaoModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.imports.AnotacaoImport;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AtributoArquivo {
  private String nome;
  private String mensagem;
  private String tipo;
  private List<String> validadores;
  private List<String> web;
  private List<String> type;
  private boolean id;
  private Mapeamento mapeamento;
  private boolean semCascadeDesnomalizado = false;
  private String tipoOrigem;


  public List<String> getValidadores() {
    if (this.validadores == null)
      this.validadores = new ArrayList<>();
    return validadores;
  }

  public List<AnotacaoModel> getAnotacoes() {
    List<AnotacaoModel> anotacoes = new ArrayList<>();
    for (String validador : this.getValidadores()) {
      AnotacaoImport importAnotacao = getAnotacaoImportacao(validador);
      anotacoes.add(new AnotacaoModel(importAnotacao.getAnotacao(), importAnotacao.getNomeImport(),
          this.getMensagem(), this.getComplementoAnotacao(validador)));
    }

    if (this.isEnum()) {
      anotacoes.add(new AnotacaoModel("@Convert", "jakarta.persistence.Convert", null,
          "converter = " + this.getNomeConverter() + ".class"));
    }

    if (this.isWebGet() || this.isPesquisa()) {
      anotacoes.add(new AnotacaoModel("@Getter", "lombok.Getter"));
    }

    return anotacoes;
  }


  private AnotacaoImport getAnotacaoImportacao(String validador) {
    return GeradorImports.get(this.getNomeAnotacaoValidador(validador));
  }


  private String getNomeAnotacaoValidador(String validador) {
    if (validador == null)
      return "";
    return validador.split(";")[0];
  }


  private String getComplementoAnotacao(String validador) {
    if (validador.split(";").length > 1) {
      return validador.split(";")[1];
    }
    return null;
  }

  public boolean isEnum() {
    return tipo.toLowerCase().contains("type");
  }

  private boolean isWebGet() {
    return this.getWeb().stream().anyMatch(item -> item.toLowerCase().contains("get"));
  }

  public List<String> getWeb() {
    if (this.web == null)
      this.web = new ArrayList<>();
    return this.web;
  }

  public boolean isPesquisa() {
    return this.web.stream().anyMatch(item -> item.toLowerCase().contains("pesquisa"));
  }

  private String getNomeConverter() {
    return this.tipo.replace("Type", "Converter");
  }


  public boolean existeMapeamento() {
    return this.mapeamento != null;
  }

  public boolean existeMetodo(MetodoType metodo) {
    return this.web.stream().anyMatch(met -> metodo.getNome().equals(met.toLowerCase()));

  }

  public boolean isEntity() {
    return this.tipo.toLowerCase().contains("entity");
  }

  public AtributosModel toAtributoModel() {
    return new AtributosModel(nome, mensagem, tipo, getAnotacoes());
  }

  public AtributosModel toAtributoDesnormalizadoModel() {
    return new AtributosModel(nome, mensagem, tipo, List.of());
  }

  public AtributosModel toAtributoDesnormalizadoComAnotacoesModel() {
    AtributosModel atributo = new AtributosModel(nome, mensagem, tipo, this.getAnotacoes());
    atributo.setSemCascadeDesnomalizado(getSemCascadeDesnomalizado());
    atributo.setTipoOrigem(getTipoOrigem());
    return atributo;
  }

  public boolean isCascade() {
    return this.mapeamento != null && this.mapeamento.isCascade();
  }

  public String getNomeAtributoId() {
    return "cd" + this.getNome().substring(0, 1).toUpperCase()
        + this.getNome().substring(1, this.getNome().length());
  }


  public AtributoArquivo(String nome, String mensagem, String tipo, List<String> validadores) {
    super();
    this.nome = nome;
    this.mensagem = mensagem;
    this.tipo = tipo;
    this.validadores = validadores;
  }

  public boolean isNotNull() {
    return this.getValidadores().stream().anyMatch(vl -> vl.toLowerCase().contains("notnull"));
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

  public boolean isString() {
    return this.tipo.toLowerCase().contains("string");
  }
}
