package br.org.ici.saude.geradorcodigo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.angular.geradores.GeradorAppRoutingAngular;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.geradores.ProcessadorArquivoAngular2;
import br.org.ici.saude.geradorcodigo.geradores.ProcessadorArquivoJava;
import freemarker.template.Configuration;

public class Main {
  public static void main(String[] args) throws Exception {

    Configuration cfg = ConfiguracaoTemplate.getConfiguracao();
    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao.json";
    ArquivoConfiguracao arquivoConfiguracao = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);

    // gerarCodigoJava(cfg, arquivoConfiguracao);
    gerarCodigoAngular2(cfg, arquivoConfiguracao);
  }

  private static void gerarCodigoAngular2(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) throws Exception {
    List<ArquivoFonte> arquivos = Stream.of(ArquivoAngularType.values())
        .map(arquivoType -> new ProcessadorArquivoAngular2(cfg, arquivoConfiguracao, arquivoType)
            .gerarArquivos())
        .flatMap(List::stream).toList();

    String diretorioClasse = "/src/app/modules";
    arquivos.stream()
        .forEach(arquivoFonte -> ArquivoUtil.escreverCodigoFonte(
            arquivoConfiguracao.getDiretorioProjetoAngular() + diretorioClasse
                + arquivoFonte.getCaminho(),
            arquivoFonte.getNomeArquivo(), arquivoFonte.getArquivo()));
    String declaraRoutingAppRouting =
        new GeradorAppRoutingAngular().converterParaArquivoModel(cfg, arquivoConfiguracao);
    String caminhoApprouting = arquivoConfiguracao.getDiretorioProjetoAngular() + diretorioClasse
        + "/app-routing.module.ts";
    ArquivoUtil.escreverEmArquivoExistente("];", caminhoApprouting, declaraRoutingAppRouting);

  }


  private static void gerarCodigoJava(Configuration cfg, ArquivoConfiguracao arquivoConfiguracao)
      throws IOException {

    List<ArquivoFonte> arquivos = Stream.of(ArquivoJavaType.values())
        .map(arquivoType -> new ProcessadorArquivoJava(cfg, arquivoConfiguracao, arquivoType)
            .gerarArquivos())
        .flatMap(List::stream).toList();

    arquivos.stream().forEach(arquivoFonte -> {
      String diretorioClasse = "/src/main/java";
      if (arquivoFonte.isTest()) {
        diretorioClasse = "/src/test/java";
      }
      ArquivoUtil.escreverCodigoFonte(
          arquivoConfiguracao.getDiretorioProjetoJava() + diretorioClasse
              + arquivoFonte.getCaminho(),
          arquivoFonte.getNomeArquivo(), arquivoFonte.getArquivo());
    });
  }
}
