package br.org.ici.saude.geradorcodigo.entidade;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AnotacaoModel {
  private String anotacao;
  private String codigoImport;
  private String mensagem;
  private String complemento;



  private boolean existeMensagemParaAnotacao(String anotacao) {
    if (anotacao != null
        && (anotacao.toLowerCase().contains("getter") || anotacao.toLowerCase().contains("setter")
            || anotacao.toLowerCase().contains("convert"))) {
      return false;
    }
    return true;
  }


  public Boolean getExisteMensagem() {
    return mensagem != null;
  }

  public Boolean getExisteComplemento() {
    return complemento != null;
  }

  public Boolean getExisteComplementoEMensagem() {
    return this.getExisteComplemento() && this.getExisteMensagem();
  }

  public boolean naoNulo() {
    return anotacao.toLowerCase().contains("notnull") || anotacao.toLowerCase().contains("notblank")
        || anotacao.toLowerCase().contains("notempty");
  }

  public Boolean anotacaoGet() {
    return this.anotacao.toLowerCase().contains("getter");
  }

  public boolean anotacaoDeveEstarNoConstrutor() {
    if (anotacao.toLowerCase().contains("@id")) {
      return false;
    }
    return this.naoNulo();
  }

  public String anotacaoUsadaNoConstrutor() {
    if (!anotacao.contains("("))
      return anotacao;
    return anotacao.substring(0, anotacao.indexOf("("));
  }


  public AnotacaoModel(String anotacao, String codigoImport, String mensagem, String complemento) {
    super();
    this.anotacao = anotacao;
    this.codigoImport = codigoImport;
    if (this.existeMensagemParaAnotacao(mensagem)) {
      this.mensagem = mensagem;
    }
    this.complemento = complemento;
  }


  public AnotacaoModel(String anotacao, String codigoImport) {
    super();
    this.anotacao = anotacao;
    this.codigoImport = codigoImport;
  }


  public boolean existeImport() {
    return this.codigoImport != null && !this.codigoImport.isBlank();
  }


  public boolean isGetter() {
    return this.anotacao.toLowerCase().contains("getter");
  }


  public boolean isConverter() {
    return this.anotacao.toLowerCase().contains("convert");
  }


  public boolean isNotNull() {
    return this.anotacao.toLowerCase().contains("notnull");
  }



}
