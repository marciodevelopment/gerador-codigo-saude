package br.org.ici.saude.geradorcodigo.common;

import br.org.ici.saude.geradorcodigo.angular.GeradorAngularType;
import br.org.ici.saude.geradorcodigo.angular.GeradorAtualizacaoAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.GeradorGetAngularResponse;
import br.org.ici.saude.geradorcodigo.angular.GeradorModuleAngular;
import br.org.ici.saude.geradorcodigo.angular.GeradorNovoAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.GeradorPesquisaAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.GeradorPesquisaAngularResponse;
import br.org.ici.saude.geradorcodigo.angular.GeradorRotasAngular;
import br.org.ici.saude.geradorcodigo.angular.GeradorServiceAngular;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.Getter;

@Getter
public enum ArquivoAngularType {


  MODULE(".module", "", "angular/moduleAngularTemplate", new GeradorModuleAngular()),

  ROUTES(".routing", "", "angular/routingAngularTemplate", new GeradorRotasAngular()),

  TYPE("Type", ".types", "angular/typeAngularTemplate", new GeradorAngularType()),

  GET_RESPONSE("Response", ArquivoAngularType.INTERFACES_RESPONSE,
      "angular/interfaceAngularTemplate", new GeradorGetAngularResponse()),

  PESQUISA_RESPONSE("PesquisaResponse", ArquivoAngularType.INTERFACES_RESPONSE,
      "angular/interfaceAngularTemplate", new GeradorPesquisaAngularResponse()),

  NOVO_REQUEST("NovoRequest", ArquivoAngularType.INTERFACES_REQUEST,
      "angular/interfaceAngularTemplate", new GeradorNovoAngularRequest()),

  ATUALIZACAO_REQUEST("AtualizacaoRequest", ArquivoAngularType.INTERFACES_REQUEST,
      "angular/interfaceAngularTemplate", new GeradorAtualizacaoAngularRequest()),

  PESQUISA_REQUEST("PesquisaRequest", ArquivoAngularType.INTERFACES_REQUEST,
      "angular/interfaceAngularTemplate", new GeradorPesquisaAngularRequest()),

  SERVICE("Service", ".service", "angular/serviceAngularTemplate", new GeradorServiceAngular());


  private static final String INTERFACES_RESPONSE = ".interfaces.response";
  private static final String INTERFACES_REQUEST = ".interfaces.request";
  private String sufixo;
  private String pacote;
  private String nomeTemplate;
  private GeradorArquivo geradorArquivo;

  ArquivoAngularType(String sufixo, String pacote, String nomeTemplate,
      GeradorArquivo geradorArquivo) {
    this.sufixo = sufixo;
    this.pacote = pacote;
    this.nomeTemplate = nomeTemplate;
    this.geradorArquivo = geradorArquivo;
  }

  public String getNomeArquivo(String nomeEntidade) {
    return nomeEntidade + this.sufixo + ".ts";
  }

  public String getPacoteArquivo(String pacoteProjeto, String pacote) {
    String modulo = "";
    if (!pacote.isBlank())
      modulo = pacote.replace(pacoteProjeto + ".", "");
    return ArquivoUtil.converterPacoteParaPathArquivo(modulo + this.pacote);
  }

}
