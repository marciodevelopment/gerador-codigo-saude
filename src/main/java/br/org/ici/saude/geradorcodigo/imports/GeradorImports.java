package br.org.ici.saude.geradorcodigo.imports;

import java.io.IOException;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;

public class GeradorImports {
  private static List<AnotacaoImport> anotacoesImport;

  private GeradorImports() {}

  static {
    try {
      anotacoesImport = ArquivoUtil.lerLinhasArquivoResources("configuracaoImport").stream()
          .map(linha -> new AnotacaoImport(linha.split(";")[0], "@" + linha.split(";")[0],
              linha.split(";")[1]))
          .toList();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static AnotacaoImport get(String nomeAnotacao) {
    return anotacoesImport.stream()
        .filter(anotacaoImport -> anotacaoIguais(nomeAnotacao, anotacaoImport)).findFirst()
        .orElse(new AnotacaoImport("",
            nomeAnotacao.contains("@") ? nomeAnotacao : "@" + nomeAnotacao, ""));
  }


  private static boolean anotacaoIguais(String nomeAnotacao, AnotacaoImport anotacaoImport) {
    return nomeAnotacao != null && !nomeAnotacao.isBlank() && (anotacaoImport.getAnotacao()
        .toLowerCase().contains(nomeAnotacao.toLowerCase().replace("@", "")));
  }


  public static String getImport(String tipo) {
    AnotacaoImport anotacoesImport = get(tipo);
    return anotacoesImport.getNomeImport();
  }

}
