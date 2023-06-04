package br.org.ici.saude.geradorcodigo.entidade;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class MapeamentoModelTest {

  @Test
  void existeOneToOneTest() {
    MapeamentoModel model =
        new MapeamentoModel("oneToONe", false, "ALL", false, "Cidade", "usuario");
    assertTrue(model.getExisteOneToOne());
  }

  @Test
  void naoExisteOneToOneTest() {
    MapeamentoModel model =
        new MapeamentoModel("manytoone", false, "ALL", false, "Cidadae", "usuario");
    assertFalse(model.getExisteOneToOne());
  }

}
