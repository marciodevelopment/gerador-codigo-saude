package br.org.ici.saude.geradorcodigo.geradores;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.exception.ProcessadorException;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessadorArquivoJava {

  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;
  private final ArquivoJavaType arquivoType;

  public List<ArquivoFonte> gerarArquivos() throws ProcessadorException {
    return this.gerarArquivo(cfg, arquivoConfiguracao);
  }

  private Writer gerarTemplate(Template builderTemplate, ArquivoModel arqModel) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(arqModel, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }

  private List<ArquivoFonte> gerarArquivo(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws ProcessadorException {
    try {

      Template builderTemplate = cfg.getTemplate(arquivoType.getNomeTemplate());
      return arquivoType.getGeradorArquivo().converterParaArquivoModel(arquivoConfiguracao).stream()
          .map(arqModel -> new ArquivoFonte(arqModel, arquivoType,
              gerarTemplate(builderTemplate, arqModel).toString()))
          .toList();
    } catch (Exception e) {
      throw new ProcessadorException(e);
    }
  }



}
