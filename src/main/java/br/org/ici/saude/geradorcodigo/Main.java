package br.org.ici.saude.geradorcodigo;

import java.util.List;
import java.util.stream.Stream;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
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

    List<ArquivoFonte> arquivos = Stream.of(ArquivoType.values())
        .map(arquivoType -> new ProcessadorArquivo(cfg, arquivoConfiguracao, arquivoType)
            .gerarArquivos())
        .flatMap(List::stream).toList();

    arquivos.stream()
        .forEach(arquivoFonte -> ArquivoUtil.escreverCodigoFonte(
            arquivoConfiguracao.getDiretorioProjetoJava() + "/src/main/java"
                + arquivoFonte.getCaminho(),
            arquivoFonte.getNomeArquivo(), arquivoFonte.getArquivo()));
  }
}
