package br.org.ici.saude.geradorcodigo.angular;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import br.org.ici.saude.geradorcodigo.common.ArquivoUtil;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;

class GeradorRotasAngularTest {

  @Test
  void converterParaArquivoModelTest() throws IOException {
    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao-test-rotas.json";
    ArquivoConfiguracao arquivo = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);
    /*
     * List<EntidadeArquivo> entidadesPath = arquivo.getEntidades().stream().filter(entidade ->
     * entidade.existePath()).toList(); assertEquals(3, entidadesPath.size()); // enndereco ->
     * cidades, estados // usuario -> usuarios // retornar dois arquivos. // ${nomeComponent} ->
     * Usuario // nome modulo. pessoa List<RotasModel> rotas = entidadesPath.stream() .map(entidade
     * -> new ModuloRotasModel(entidade.getNome(),
     * entidade.getPacote().replace(arquivo.getPacoteProjeto() + ".", ""),
     * entidade.existePesquisa(), entidade.existeAtualizacao(), entidade.existeNovo(),
     * entidade.getPath(), entidade.getPacote().replace(arquivo.getPacoteProjeto() + ".",
     * "").toUpperCase())) .toList(); assertEquals(3, rotas.size());
     * 
     * Map<String, List<RotasModel>> rotasAgrupadas =
     * rotas.stream().collect(Collectors.groupingBy(RotasModel::getNomeModulo)); assertEquals(2,
     * rotasAgrupadas.size());
     * 
     * System.out.println(rotasAgrupadas);
     */
  }

}
