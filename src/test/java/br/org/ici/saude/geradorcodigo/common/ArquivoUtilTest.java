package br.org.ici.saude.geradorcodigo.common;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.AtributoArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;

class ArquivoUtilTest {

  @Test
  void lerArquivo() throws IOException {
    List<String> linhas = ArquivoUtil.lerLinhasArquivoResources("configuracaoImport");
    assertFalse(linhas.isEmpty());
  }


  @Test
  void converterPacoteParaPathArquivoTest() {
    assertEquals("/br/org/ici/saude/usuario/endereco/",
        ArquivoUtil.converterPacoteParaPathArquivo("br.org.ici.saude.usuario.endereco"));
  }

  @Test
  void lerJsonTest() throws IOException {
    String path =
        "/home/marcio/eclipse-workspace/geradorcodigo/src/main/resources/configuracao.json";
    ArquivoConfiguracao arquivo = ArquivoUtil.lerJson(path, ArquivoConfiguracao.class);
    assertEquals(arquivo.getDiretorioProjeto(), "/home/marcio/Desktop/arquivos-gerador/usuario");
    EntidadeArquivo classe = arquivo.getEntidades().get(2);
    assertEquals("br.org.ici.saude.usuario", classe.getPacote());
    assertEquals("usuarios", classe.getPath());
    assertEquals("Usuário", classe.getMensagem());
    assertEquals("Usuario", classe.getNome());
    assertEquals(true, classe.isGerarAuditoria());
    assertEquals("[post, put, get, delete, pesquisa]", classe.getMetodos().toString());
    AtributoArquivo atributo = classe.getAtributos().get(1);
    assertEquals("nmUsuario", atributo.getNome());
    assertEquals("Nome usuário", atributo.getMensagem());
    assertEquals("String", atributo.getTipo());
    assertEquals("[notEmpty, min;size=10]", atributo.getValidadores().toString());
    assertEquals("[get, put, post, pesquisa]", atributo.getWeb().toString());
  }

}
