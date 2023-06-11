package br.org.ici.saude.geradorcodigo.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import br.org.ici.saude.geradorcodigo.common.exception.ArquivoUtilException;

public class ArquivoUtil {

  public static <T> T lerJson(String path, Class<T> clazz) throws IOException {
    Gson gson = new Gson();
    String json = Files.readString(Path.of(path));
    return gson.fromJson(json, clazz);
  }

  public static String converterPacoteParaPathArquivo(String pacote) {
    return "/" + pacote.replace(".", "/") + "/";
  }

  public static List<String> lerLinhasArquivoResources(String nomeArquivo) throws IOException {
    return Files.readAllLines(Paths.get(ClassLoader.getSystemResource(nomeArquivo).getPath()));
  }

  public static void escreverCodigoFonte(String caminho, String nomeArquivo, String arquivo) {
    try {
      Path path = Paths.get(caminho);
      if (!Files.exists(path))
        Files.createDirectories(path);
      Path pathArquivo = Paths.get(caminho + nomeArquivo);
      if (Files.exists(pathArquivo))
        return;
      Files.createFile(pathArquivo);
      Files.writeString(pathArquivo, arquivo);
    } catch (IOException e) {
      throw new ArquivoUtilException(e);
    }
  }

}
