package br.org.ici.saude.geradorcodigo.imports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GeradorImportsTest {

  @Test
  void deverRetonarParaEnvioSemArroba() {
    assertEquals("lombok.Getter", GeradorImports.get("getter").getNomeImport());
    assertEquals("@Getter", GeradorImports.get("getter").getAnotacao());

  }

  @Test
  void deverRetonarParaEnvioComArroba() {
    assertEquals("lombok.Getter", GeradorImports.get("@getter").getNomeImport());
    assertEquals("@Getter", GeradorImports.get("@getter").getAnotacao());

  }

  @Test
  void deverRetonarAPropriaAnotacaoSeNaoAchar() {
    assertEquals("", GeradorImports.get("teste").getNomeImport());
    assertEquals("@teste", GeradorImports.get("teste").getAnotacao());

  }

}
