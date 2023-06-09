package br.org.ici.saude.geradorcodigo.geradores;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.entidade.PesquisaViewModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorViewPesquisa {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template builderTemplate = cfg.getTemplate("pesquisaViewTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();

    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.existePesquisa()).toList()) {

      FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
      Collection<AtributosModel> atributosFiltratos = filtroAtributos
          .getAtributosDesnormalizadosModel(entidadeArq.getNome(), MetodoType.PESQUISA);


      PesquisaViewModel pesquisaView =
          new PesquisaViewModel(entidadeArq.getNome(), entidadeArq.getPacote());
      pesquisaView.addAtributos(atributosFiltratos);

      arquivos.add(new ArquivoFonte(entidadeArq.getNome() + "PesquisaView.java",
          ArquivoUtil.converterPacoteParaPathArquivo(entidadeArq.getPacote() + ".entity.view"),
          gerarTemplate(builderTemplate, pesquisaView).toString()));

    }
    return arquivos;
  }


  private Writer gerarTemplate(Template builderTemplate, PesquisaViewModel type) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(type, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }



}
