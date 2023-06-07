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
import br.org.ici.saude.geradorcodigo.repositorio.ServiceModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorService {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template serviceTemplate = cfg.getTemplate("serviceTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();
    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.existeMetodos()).toList()) {
      ServiceModel serviceModel = new ServiceModel(entidadeArq.getNome(), entidadeArq.getPacote(),
          entidadeArq.getMensagem(), entidadeArq.getMetodos());
      arquivos.add(new ArquivoFonte(entidadeArq.getNome() + "Service.java",
          ArquivoUtil.converterPacoteParaPathArquivo(entidadeArq.getPacote() + ".service"),
          gerarTemplate(serviceTemplate, serviceModel).toString()));

    }
    return arquivos;
  }


  private Writer gerarTemplate(Template builderTemplate, ServiceModel type) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(type, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }



}
