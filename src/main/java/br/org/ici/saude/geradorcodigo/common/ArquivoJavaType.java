package br.org.ici.saude.geradorcodigo.common;

import br.org.ici.saude.geradorcodigo.entidade.GeradorConverter;
import br.org.ici.saude.geradorcodigo.entidade.GeradorEntidade;
import br.org.ici.saude.geradorcodigo.entidade.GeradorType;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import br.org.ici.saude.geradorcodigo.geradores.GeradorBuilder;
import br.org.ici.saude.geradorcodigo.repositorio.GeradorRepository;
import br.org.ici.saude.geradorcodigo.service.GeradorService;
import br.org.ici.saude.geradorcodigo.web.GeradorAtualizacaoRequest;
import br.org.ici.saude.geradorcodigo.web.GeradorController;
import br.org.ici.saude.geradorcodigo.web.GeradorGetResponse;
import br.org.ici.saude.geradorcodigo.web.GeradorMapper;
import br.org.ici.saude.geradorcodigo.web.GeradorNovoRequest;
import br.org.ici.saude.geradorcodigo.web.GeradorPesquisaRequest;
import br.org.ici.saude.geradorcodigo.web.GeradorPesquisaResponse;
import br.org.ici.saude.geradorcodigo.web.GeradorViewPesquisa;
import lombok.Getter;

@Getter
public enum ArquivoJavaType {
  ENTITY("Entity", ".entity", "entidadeTemplate", new GeradorEntidade()),

  TYPE("Type", ".entity.type", "typeTemplate", new GeradorType()),

  BUILDER("NovoBuilder", ".entity", "builderTemplate", new GeradorBuilder()),

  CONVERTER("Converter", ".entity.type", "converterTemplate", new GeradorConverter()),

  VIEW_PESQUISA("PesquisaView", ".entity.view", "pesquisaViewTemplate", new GeradorViewPesquisa()),

  REPOSITORY("Repository", ".repository", "repositorioTemplate", new GeradorRepository()),

  SERVICE("Service", ".service", "serviceTemplate", new GeradorService()),

  CONTROLLER("Controller", ".rest.web", "controllerTemplate", new GeradorController()),

  CONTROLLER_TESTE("ControllerIntegarionTest", ".rest.web", "controllerIntegrationTestTemplate",
      new GeradorController()),

  ATUALIZACAO_REQUEST("AtualizacaoRequest", ".rest.web.request", "atualizacaoRequestTemplate",
      new GeradorAtualizacaoRequest()),

  PESQUISA_REQUEST("PesquisaRequest", ".rest.web.request", "pesquisaRequestTemplate",
      new GeradorPesquisaRequest()),

  NOVO_REQUEST("NovoRequest", ".rest.web.request", "novoRequestTemplate", new GeradorNovoRequest()),

  GET_RESPONSE("Response", ".rest.web.response", "getResponseTemplate", new GeradorGetResponse()),

  PESQUISA_RESPONSE("PesquisaResponse", ".rest.web.response", "pesquisaResponseTemplate",
      new GeradorPesquisaResponse()),

  MAPPER("Mapper", ".rest.mapper", "mapperTemplate", new GeradorMapper());

  private String sufixo;
  private String pacote;
  private String nomeTemplate;
  private GeradorArquivo geradorArquivo;

  ArquivoJavaType(String sufixo, String pacote, String nomeTemplate, GeradorArquivo geradorArquivo) {
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
