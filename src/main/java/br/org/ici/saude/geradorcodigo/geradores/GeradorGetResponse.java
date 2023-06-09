package br.org.ici.saude.geradorcodigo.geradores;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.web.GetResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorGetResponse {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template builderTemplate = cfg.getTemplate("getResponseTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();
    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.existePesquisa()).toList()) {

      FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
      Collection<AtributosModel> atributosFiltratos =
          filtroAtributos.getAtributosDesnormalizadosModel(entidadeArq.getNome(), MetodoType.GET);

      GetResponse model =
          new GetResponse(entidadeArq.getNome(), entidadeArq.getPacote(), atributosFiltratos);

      arquivos.add(new ArquivoFonte(entidadeArq.getNome() + "Response.java",
          ArquivoUtil.converterPacoteParaPathArquivo(entidadeArq.getPacote() + ".web.response"),
          gerarTemplate(builderTemplate, model).toString()));

    }
    return arquivos;
  }


  private Writer gerarTemplate(Template builderTemplate, BaseModel model) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(model, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }
}
