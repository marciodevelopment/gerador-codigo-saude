package br.org.ici.saude.geradorcodigo.configuracao;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;

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

  @Test
  void extairAtributosRemovendoAnotacoesDeRequest() throws IOException {
    String path = "src/test/resources/teste-anotacoes-request.json";
    ArquivoConfiguracao arquivoConfiguracao = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);
    FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
    List<AtributosModel> atribtutos =
        filtroAtributos.getAtributosDesnormalizadosParaRequest("Cidade", "cidades", MetodoType.PUT);
    boolean existeConverter = atribtutos.stream().anyMatch(atr -> atr.getExisteAnotacaoConverter());
    assertFalse(existeConverter);
  }


  @Test
  void extairAtributosEntityRequestPost() throws IOException {
    String path = "src/test/resources/teste-desnormalizacao-put-entidade.json";
    ArquivoConfiguracao arquivoConfiguracao = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);
    FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
    List<AtributosModel> atribtutos = filtroAtributos
        .getAtributosDesnormalizadosParaRequest("Usuario", "Usuarios", MetodoType.POST);
    int expected = 4;
    assertEquals(expected, atribtutos.size());

  }



}
