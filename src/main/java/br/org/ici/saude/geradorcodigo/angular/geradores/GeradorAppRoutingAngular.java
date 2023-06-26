package br.org.ici.saude.geradorcodigo.angular.geradores;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.angular.common.AngularUtil;
import br.org.ici.saude.geradorcodigo.angular.models.AppRoutingAngularModel;
import br.org.ici.saude.geradorcodigo.angular.models.AppRoutingPathAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class GeradorAppRoutingAngular {


  public String converterParaArquivoModel(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    Set<AppRoutingAngularModel> appRouting =
        arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existePath())
            .map(atributo -> new AppRoutingAngularModel(
                AngularUtil.extrairNomeVariavelModulo(atributo.getPacote()),
                AngularUtil.extrairNomeModulo(atributo.getPacote())))
            .collect(Collectors.toSet());
    Template builderTemplate = cfg.getTemplate("angular/appRoutingTemplate");
    String rotas =
        gerarTemplate(builderTemplate, new AppRoutingPathAngularModel(appRouting)).toString();
    return rotas;
  }

  private Writer gerarTemplate(Template builderTemplate, Object model) {
    Writer writer = new StringWriter();
    try {
      builderTemplate.process(model, writer);
    } catch (TemplateException | IOException e) {
      e.printStackTrace();
    }
    return writer;
  }

}
