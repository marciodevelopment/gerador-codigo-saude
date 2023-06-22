package br.org.ici.saude.geradorcodigo.common;

import br.org.ici.saude.geradorcodigo.entidade.GeradorType;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import br.org.ici.saude.geradorcodigo.service.GeradorService;
import br.org.ici.saude.geradorcodigo.web.GeradorAtualizacaoRequest;
import br.org.ici.saude.geradorcodigo.web.GeradorGetResponse;
import br.org.ici.saude.geradorcodigo.web.GeradorNovoRequest;
import br.org.ici.saude.geradorcodigo.web.GeradorPesquisaRequest;
import br.org.ici.saude.geradorcodigo.web.GeradorPesquisaResponse;
import lombok.Getter;

@Getter
public enum ArquivoAngularType {
  TYPE("Type", "types", "typeAngularTemplate", new GeradorType()),

  SERVICE("Service", ".service", "serviceTemplate", new GeradorService()),

  ATUALIZACAO_REQUEST("AtualizacaoRequest", ".rest.web.request", "atualizacaoRequestTemplate",
      new GeradorAtualizacaoRequest()),

  PESQUISA_REQUEST("PesquisaRequest", ".rest.web.request", "pesquisaRequestTemplate",
      new GeradorPesquisaRequest()),

  NOVO_REQUEST("NovoRequest", ".rest.web.request", "novoRequestTemplate", new GeradorNovoRequest()),

  GET_RESPONSE("Response", ".rest.web.response", "getResponseTemplate", new GeradorGetResponse()),

  PESQUISA_RESPONSE("PesquisaResponse", ".rest.web.response", "pesquisaResponseTemplate",
      new GeradorPesquisaResponse());

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
    return nomeEntidade + this.sufixo + ".java";
  }

  public String getPacoteArquivo(String nomePacote) {
    return ArquivoUtil.converterPacoteParaPathArquivo(nomePacote + this.pacote);
  }

}
