package br.org.ici.saude.geradorcodigo.configuracao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;

class FiltroAtributosTest {

  @Test
  void extrairAtributosTest() throws IOException {
    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao_teste_desnormalizacao.json";
    ArquivoConfiguracao arquivo = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);

    FiltroAtributos atributoDesnomalizado = new FiltroAtributos(arquivo);
    Collection<AtributoArquivo> atributos =
        atributoDesnomalizado.getAtributosDesnormalizados("Usuario", MetodoType.PESQUISA);

    assertEquals(2, atributos.size());
  }

  @Test
  void extrairAtributosConfiguracaoTest() throws IOException {
    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao.json";
    ArquivoConfiguracao arquivo = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);

    FiltroAtributos atributoDesnomalizado = new FiltroAtributos(arquivo);
    Collection<AtributoArquivo> atributos =
        atributoDesnomalizado.getAtributosDesnormalizados("Usuario", MetodoType.PESQUISA);

    assertEquals(7, atributos.size());
  }

}
