package br.org.ici.saude.geradorcodigo;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.entidade.EntidadeModel;
import br.org.ici.saude.geradorcodigo.entidade.PesquisaViewModel;
import br.org.ici.saude.geradorcodigo.entidade.TypeModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorEntidade;
import br.org.ici.saude.geradorcodigo.repositorio.RepositorioModel;
import br.org.ici.saude.geradorcodigo.repositorio.ServiceModel;
import br.org.ici.saude.geradorcodigo.web.MapperModel;
import br.org.ici.saude.geradorcodigo.web.PesquisaResponseModel;
import br.org.ici.saude.geradorcodigo.web.ResponseRequestModel;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class Main {
  public static void main(String[] args) throws Exception {

    Configuration cfg = ConfiguracaoTemplate.getConfiguracao();

    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao.json";
    ArquivoConfiguracao arquivoConfiguracao = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);


    GeradorEntidade geradorEntidade = new GeradorEntidade(cfg, arquivoConfiguracao);
    List<ArquivoFonte> arquivos = geradorEntidade.gerarArquvos();
    // GeradorBuilder geradorBuilder = new GeradorBuilder(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorBuilder.gerarArquvos());
    //
    // GeradorType geradorType = new GeradorType(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorType.gerarArquvos());
    //
    // GeradorConverter geradorConverter = new GeradorConverter(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorConverter.gerarArquvos());
    //
    //
    // GeradorRepository geradorRepository = new GeradorRepository(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorRepository.gerarArquvos());
    //

    //
    //
    // GeradorService geradorService = new GeradorService(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorService.gerarArquvos());
    //
    //

    // GeradorMapper geradorMapper = new GeradorMapper(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorMapper.gerarArquvos());

    // GeradorViewPesquisa geradorView = new GeradorViewPesquisa(cfg, arquivoConfiguracao);
    // arquivos.addAll(geradorView.gerarArquvos());

    // GeradorPesquisaResponse geradorPesquisaResponse = new GeradorPesquisaResponse(cfg,
    // arquivoConfiguracao);
    // arquivos.addAll(geradorPesquisaResponse.gerarArquvos());


    // GeradorGetResponse getResponseGerador = new GeradorGetResponse(cfg, arquivoConfiguracao);
    // arquivos.addAll(getResponseGerador.gerarArquvos());

    arquivos.stream().forEach(arquivoFonte -> {
      try {
        ArquivoUtil.escreverCodigoFonte(
            arquivoConfiguracao.getDiretorioProjetoJava() + "/src/main/java"
                + arquivoFonte.getCaminho(),
            arquivoFonte.getNomeArquivo(), arquivoFonte.getArquivo());
      } catch (Exception e) {
        e.printStackTrace();
      }
    });


    // gerarType(cfg);
    // gerarConverter(cfg);
    // gerarEntidade(cfg);
    // gerarBuilder(cfg);
    // gerarRepositorio(cfg);
    // gerarPesquisaView(cfg);
    // gerarService(cfg);
    // gerar o request e response
    // gerar mapstruct
    // gerar o controller

    // gerarMappeer(cfg, arquivoConfiguracao);

    // gerarResponsePesquisa(cfg, arquivoConfiguracao);


    // gerarAtualizacaoRequest(cfg, arquivoConfiguracao);
    // gerarAtualizacaoRequest(cfg, arquivoConfiguracao);
    // gerarNovoRequest(cfg, arquivoConfiguracao);

    // gerarPesquisaRequest(cfg, arquivoConfiguracao);
  }


  private static void gerarPesquisaRequest(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template template = cfg.getTemplate("pesquisaRequestTemplate");
    FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);

    List<AtributosModel> atributos = filtroAtributos.getAtributosDesnormalizadosModel(
        arquivoConfiguracao.getEntidades().get(2).getNome(), MetodoType.POST);

    ResponseRequestModel model =
        new ResponseRequestModel(arquivoConfiguracao.getEntidades().get(2).getNome(),
            arquivoConfiguracao.getEntidades().get(2).getPacote(), atributos);

    Writer out = new OutputStreamWriter(System.out);
    template.process(model, out);

  }


  private static void gerarNovoRequest(Configuration cfg, ArquivoConfiguracao arquivoConfiguracao)
      throws Exception {
    Template template = cfg.getTemplate("novoRequestTemplate");
    FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);

    List<AtributosModel> atributos = filtroAtributos.getAtributosDesnormalizadosParaRequest(
        arquivoConfiguracao.getEntidades().get(2).getNome(),
        arquivoConfiguracao.getEntidades().get(2).getMensagem(), MetodoType.POST);

    ResponseRequestModel model =
        new ResponseRequestModel(arquivoConfiguracao.getEntidades().get(2).getNome(),
            arquivoConfiguracao.getEntidades().get(2).getPacote(), atributos);

    Writer out = new OutputStreamWriter(System.out);
    template.process(model, out);

  }


  private static void gerarAtualizacaoRequest(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template template = cfg.getTemplate("atualizacaoRequestTemplate");
    FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);

    List<AtributosModel> atributos = filtroAtributos.getAtributosDesnormalizadosParaRequest(
        arquivoConfiguracao.getEntidades().get(2).getNome(),
        arquivoConfiguracao.getEntidades().get(2).getMensagem(), MetodoType.POST);

    ResponseRequestModel model =
        new ResponseRequestModel(arquivoConfiguracao.getEntidades().get(2).getNome(),
            arquivoConfiguracao.getEntidades().get(2).getPacote(), atributos);

    Writer out = new OutputStreamWriter(System.out);
    template.process(model, out);

  }


  private static void gerarResponsePesquisa(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template template = cfg.getTemplate("pesquisaResponseTemplate");

    PesquisaResponseModel model =
        new PesquisaResponseModel(arquivoConfiguracao.getEntidades().get(2).getNome(),
            arquivoConfiguracao.getEntidades().get(2).getPacote(),
            arquivoConfiguracao.getEntidades().get(2).toEntidadeModel().getAtributosView());

    Writer out = new OutputStreamWriter(System.out);
    template.process(model, out);

  }


  private static void gerarMappeer(Configuration cfg, ArquivoConfiguracao arquivoConfiguracao)
      throws Exception {
    Template template = cfg.getTemplate("mapperTemplate");

    MapperModel model = new MapperModel(arquivoConfiguracao.getEntidades().get(2).getNome(),
        arquivoConfiguracao.getEntidades().get(2).getPacote());
    Writer out = new OutputStreamWriter(System.out);
    template.process(model, out);
  }

  private static void gerarService(Configuration cfg) throws Exception {
    Template serviceTemplate = cfg.getTemplate("serviceTemplate");
    ServiceModel serviceModel = new ServiceModel("Usuario", "br.org.ici.saude.usuario", "Usuário");
    serviceModel.setExisteDelete(true);
    serviceModel.setExisteGet(true);
    serviceModel.setExistePesquisa(true);
    serviceModel.setExistePost(true);
    serviceModel.setExistePut(true);

    Writer out = new OutputStreamWriter(System.out);
    serviceTemplate.process(serviceModel, out);

  }

  private static void gerarConverter(Configuration cfg) throws Exception {
    Template typeTemplate = cfg.getTemplate("converterTemplate");

    TypeModel model = new TypeModel("Sexo", "br.com.saude.usuario",
        Arrays.asList((new String[] {"1;MASCULINO;Masculino", "2;FEMININO;Feminino"})));



    Writer out = new OutputStreamWriter(System.out);

    typeTemplate.process(model, out);
  }

  private static void gerarType(Configuration cfg) throws Exception {
    Template typeTemplate = cfg.getTemplate("typeTemplate");
    TypeModel model = new TypeModel("Sexo", "br.com.saude.usuario",
        Arrays.asList(new String[] {"1;MASCULINO;Masculino", "2;FEMININO;Feminino"}));
    Writer out = new OutputStreamWriter(System.out);

    typeTemplate.process(model, out);
  }

  private static void gerarPesquisaView(Configuration cfg) throws Exception {
    Template entidadeTemplate = cfg.getTemplate("pesquisaViewTemplate");
    EntidadeModel entidade = getEntidade();

    PesquisaViewModel model = new PesquisaViewModel("Usuario", "br.com.usuario");
    // entidade.getAtributos().forEach(model::addAtributo);

    Writer out = new OutputStreamWriter(System.out);
    entidadeTemplate.process(model, out);
  }

  private static void gerarRepositorio(Configuration cfg) throws Exception {
    Template entidadeTemplate = cfg.getTemplate("repositorioTemplate");

    RepositorioModel repositorio =
        new RepositorioModel("Usuario", "br.org.ici.saude.usuario.usuario");
    repositorio.setExistePesquisa(true);

    repositorio.addColunaQuery("u.cdUsuario", "u.nmUsuario", "u.nmMae", "u.sexo",
        "u.documentoCpf.nrCpf");

    repositorio.setQuery("from UsuarioEntity u\n" + "          where u.cdUsuario = :cdUsuario\n"
        + "          and   u.nmUsuario like :nmUsuario\n"
        + "          and   u.nmMae     like :nmMae\n" + "          and   u.sexo      = :sexo\n"
        + "          and   u.documentoCpf.nrCpf like :nrCpf");
    Writer out = new OutputStreamWriter(System.out);
    entidadeTemplate.process(repositorio, out);

  }



  private static void gerarEntidade(Configuration cfg) throws Exception {
    Template entidadeTemplate = cfg.getTemplate("entidadeTemplate");
    EntidadeModel entidade = getEntidade();

    Writer out = new OutputStreamWriter(System.out);
    entidadeTemplate.process(entidade, out);

  }

  private static void gerarBuilder(Configuration cfg) throws Exception {
    Template builderTemplate = cfg.getTemplate("builderTemplate");
    Writer outBuilder = new OutputStreamWriter(System.out);
    builderTemplate.process(getEntidade(), outBuilder);
  }

  private static EntidadeModel getEntidade() {
    EntidadeModel entidade = new EntidadeModel("Usuario", "br.org.ici.saude.usuario");

    // AtributosModel nmUsuario = new AtributosModel("String", "nmUsuario", "Nome Usuário");

    // nmUsuario.addAnotacao("@Getter");
    // nmUsuario.addAnotacao("@NotEmpty");
    // nmUsuario.addAnotacao("@Size", "max = 255");
    // entidade.addAtributo(nmUsuario);
    //
    // AtributosModel nmMae = new AtributosModel("String", "nmMae", "Nome Mãe");
    // nmMae.addAnotacao("@NotEmpty");
    // nmMae.addAnotacao("@Getter");
    // entidade.addAtributo(nmMae);
    //
    //
    // AtributosModel dtNascimento = new AtributosModel("LocalDate", "dtNascimento", "Nome Mãe");
    // dtNascimento.addAnotacao("@Getter");
    // dtNascimento.addAnotacao("@NotNull");
    // dtNascimento.addAnotacao("@PastOrPresent");
    // entidade.addAtributo(dtNascimento);
    //
    // AtributosModel sexo = new AtributosModel("br.com.saude.usuario.type.SexoType", "sexo",
    // "Sexo");
    // sexo.addAnotacao("@Getter");
    // sexo.addAnotacao("@NotNull");
    // sexo.addAnotacao("@Convert", "converter = SexoConverter.class");
    // entidade.addAtributo(sexo);
    //
    // AtributosModel genero =
    // new AtributosModel("br.com.saude.usuario.type.GeneroType", "genero", "Genero");
    // genero.addAnotacao("@Getter");
    // genero.addAnotacao("@NotNull");
    // genero.addAnotacao("@Convert", "converter = SexoConverter.class");
    // entidade.addAtributo(genero);
    //
    //
    // AtributosModel cidade =
    // new AtributosModel("br.com.saude.CidadeEntity", "cidadeNascimento", "Cidade Nascimento");
    // cidade.addAnotacao("@NotNull");
    // entidade.addAtributo(cidade);
    // cidade.addMapeamento("ManyToOne", true, null);
    //
    // AtributosModel login =
    // new AtributosModel("br.com.saude.LoginEntity", "loginInclusao", "Login Inclusão");
    // login.addAnotacao("@NotNull");
    // entidade.addAtributo(login);
    // login.addMapeamento("ManyToOne", true, null);
    //
    // AtributosModel documentoCpf =
    // new AtributosModel("br.com.saude.DocumentoCpfEntity", "documentoCpf", "Documento CPF");
    // documentoCpf.addAnotacao("@NotNull");
    // documentoCpf.addAnotacao("@Getter");
    // entidade.addAtributo(documentoCpf);
    // documentoCpf.addMapeamento("OneToOne", false, "ALL");
    //
    // AtributosModel telefones =
    // new AtributosModel("br.com.saude.TelefoneEntity", "telefones", "Telefone");
    // entidade.addAtributo(telefones);
    // telefones.addAnotacao("@Getter");
    // telefones.addMapeamento("@OneToMany", false, "ALL");
    return entidade;
  }
}
