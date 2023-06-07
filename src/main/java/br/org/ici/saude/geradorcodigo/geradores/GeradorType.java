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
import br.org.ici.saude.geradorcodigo.entidade.TypeModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorType {
  private final Configuration cfg;
  private final ArquivoConfiguracao arquivoConfiguracao;


  public List<ArquivoFonte> gerarArquvos() throws Exception {
    return this.gerarEntidade(cfg, arquivoConfiguracao);
  }

  private List<ArquivoFonte> gerarEntidade(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Template builderTemplate = cfg.getTemplate("typeTemplate");
    List<ArquivoFonte> arquivos = new ArrayList<>();
    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades()) {
      arquivos = entidadeArq.getTypes().stream()
          .map(typeArq -> new TypeModel(typeArq.getTipo().replace("Type", ""),
              entidadeArq.getPacote(), typeArq.getType()))
          .map(type -> new ArquivoFonte(type.getNome() + "Type.java",
              ArquivoUtil.converterPacoteParaPathArquivo(type.getPacote() + ".entity.type"),
              gerarTemplate(builderTemplate, type).toString()))
          .toList();
    }
    return arquivos;
  }

  private Writer gerarTemplate(Template builderTemplate, TypeModel type) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(type, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }



}
