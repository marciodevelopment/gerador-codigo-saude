package br.org.ici.saude.geradorcodigo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoFonte;
import br.org.ici.saude.geradorcodigo.geradores.ProcessadorArquivo;
import freemarker.template.Configuration;

public class Main {
  public static void main(String[] args) throws Exception {

    Configuration cfg = ConfiguracaoTemplate.getConfiguracao();
    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao.json";
    ArquivoConfiguracao arquivoConfiguracao = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);

    gerarCodigoJava(cfg, arquivoConfiguracao);
  }

  private static void gerarCodigoAngular(Configuration cfg,
      ArquivoConfiguracao arquivoConfiguracao) {

  }

  private static void gerarCodigoJava(Configuration cfg, ArquivoConfiguracao arquivoConfiguracao)
      throws IOException {

    List<ArquivoFonte> arquivos = Stream.of(ArquivoJavaType.values())
        .map(arquivoType -> new ProcessadorArquivo(cfg, arquivoConfiguracao, arquivoType)
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
