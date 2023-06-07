package br.org.ici.saude.geradorcodigo.geradores;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.web.PesquisaResponseModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorPesquisaResponse {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template builderTemplate = cfg.getTemplate("pesquisaResponseTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();
    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.existePesquisa()).toList()) {

      PesquisaResponseModel model = new PesquisaResponseModel(entidadeArq.getNome(),
          entidadeArq.getPacote(), entidadeArq.toEntidadeModelView().getAtributosView());

      arquivos.add(new ArquivoFonte(entidadeArq.getNome() + "PesquisaResponse.java",
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
