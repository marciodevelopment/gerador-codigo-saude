package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.entidade.AnotacaoModel;
import br.org.ici.saude.geradorcodigo.imports.AnotacaoImport;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;
import lombok.Data;

@Data
public class AtributoArquivo {
  private String nome;
  private String mensagem;
  private String tipo;
  private List<String> validadores;
  private List<String> web;
  private List<String> type;
  private boolean id;
  private Mapeamento mapeamento;


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
    return this.web.stream().anyMatch(item -> item.toLowerCase().contains("get"));
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

}
