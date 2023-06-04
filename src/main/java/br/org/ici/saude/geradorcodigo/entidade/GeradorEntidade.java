package br.org.ici.saude.geradorcodigo.entidade;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorEntidade {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template entidadeTemplate = cfg.getTemplate("entidadeTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();
    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades()) {
      Writer writer = new StringWriter();
      entidadeTemplate.process(entidadeArq.toEntidadeModel(), writer);
      arquivos.add(new ArquivoFonte(entidadeArq.getNome() + "Entity.java",
          ArquivoUtil.converterPacoteParaPathArquivo(entidadeArq.getPacote()), writer.toString()));
    }
    return arquivos;
  }

}
