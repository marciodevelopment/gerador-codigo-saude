package br.org.ici.saude.geradorcodigo.angular.common;

import br.org.ici.saude.geradorcodigo.angular.GeradorServiceAngular;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorAngularType;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorAtualizacaoAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorGetAngularResponse;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorModuleAngular;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorNovoAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorPesquisaAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorPesquisaAngularResponse;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorPesquisaComponentAngularRequest;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorPesquisaComponentFormAngular;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorPesquisaComponentFormHtmlAngular;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorRotasAngular;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;
import lombok.Getter;

@Getter
public enum ArquivoAngularType {

  SERVICE("Service", "/service/", ".ts", "angular/serviceAngularTemplate",
      new GeradorServiceAngular()),

  GET_RESPONSE("Response", ArquivoAngularType.INTERFACES_RESPONSE, ".ts",
      "angular/interfaceAngularTemplate", new GeradorGetAngularResponse()),

  PESQUISA_REQUEST("PesquisaRequest", ArquivoAngularType.INTERFACES_REQUEST, ".ts",
      "angular/interfaceAngularTemplate", new GeradorPesquisaAngularRequest()),

  NOVO_REQUEST("NovoRequest", ArquivoAngularType.INTERFACES_REQUEST, ".ts",
      "angular/interfaceAngularTemplate", new GeradorNovoAngularRequest()),

  PESQUISA_RESPONSE("PesquisaResponse", ArquivoAngularType.INTERFACES_RESPONSE, ".ts",
      "angular/interfaceAngularTemplate", new GeradorPesquisaAngularResponse()),

  ATUALIZACAO_REQUEST("AtualizacaoRequest", ArquivoAngularType.INTERFACES_REQUEST, ".ts",
      "angular/interfaceAngularTemplate", new GeradorAtualizacaoAngularRequest()),

  PESQUISA_COMPONENT("-pesquisa.component", "", ".ts", "angular/pesquisaComponentAngularTemplate",
      new GeradorPesquisaComponentAngularRequest()),

  PESQUISA_F0RM_COMPONENT("-pesquisa-form.component", "", ".ts",
      "angular/pesquisaComponentFormAngularTemplate", new GeradorPesquisaComponentFormAngular()),

  PESQUISA_F0RM_COMPONENT_HTML("-pesquisa-form.component", "", ".html",
      "angular/pesquisaComponentFormHtmlAngularTemplate",
      new GeradorPesquisaComponentFormHtmlAngular()),

  PESQUISA_COMPONENT_HTML("-pesquisa.component", "", ".html",
      "angular/pesquisaComponentHtmlAngularTemplate", new GeradorPesquisaComponentAngularRequest()),

  MODULE(".module", "/", ".ts", "angular/moduleAngularTemplate", new GeradorModuleAngular()),

  ROUTES(".routing", "", ".ts", "angular/routingAngularTemplate", new GeradorRotasAngular()),

  TYPE("Type", "types/", ".ts", "angular/typeAngularTemplate", new GeradorAngularType());

  private static final String INTERFACES_RESPONSE = "/interfaces/response/";
  private static final String INTERFACES_REQUEST = "/interfaces/request/";
  private String sufixo;
  private String caminho;
  private String nomeTemplate;
  private GeradorAngularArquivo geradorArquivo;
  private String extensaoArquivo;

  ArquivoAngularType(String sufixo, String caminho, String extensaoArquivo, String nomeTemplate,
      GeradorAngularArquivo geradorArquivo) {
    this.sufixo = sufixo;
    this.caminho = caminho;
    this.nomeTemplate = nomeTemplate;
    this.geradorArquivo = geradorArquivo;
    this.extensaoArquivo = extensaoArquivo;
  }

  public String getNomeArquivo(String nomeEntidade) {
    return nomeEntidade + this.sufixo + this.extensaoArquivo;
  }

  public String caminhoArquivo(String pacoteProjeto, String pacote) {
    return ArquivoUtil.converterPacoteParaPathArquivo(pacote.replace(pacoteProjeto, ""))
        + this.caminho;
  }

}
