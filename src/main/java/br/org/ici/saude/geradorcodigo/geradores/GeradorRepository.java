package br.org.ici.saude.geradorcodigo.geradores;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.repositorio.RepositorioModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorRepository {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template builderTemplate = cfg.getTemplate("repositorioTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();

    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.existeMetodos()).toList()) {

      RepositorioModel repositorio = new RepositorioModel(entidadeArq.getNome(),
          entidadeArq.getPacote(), entidadeArq.existePesquisa(), entidadeArq.getColunasPesquisa(),
          entidadeArq.getQueryPesquisa());

      arquivos.add(new ArquivoFonte(entidadeArq.getNome() + "Repository.java",
          ArquivoUtil.converterPacoteParaPathArquivo(entidadeArq.getPacote() + ".repository"),
          gerarTemplate(builderTemplate, repositorio).toString()));

    }
    return arquivos;
  }



  private Writer gerarTemplate(Template builderTemplate, RepositorioModel type) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(type, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }



}
