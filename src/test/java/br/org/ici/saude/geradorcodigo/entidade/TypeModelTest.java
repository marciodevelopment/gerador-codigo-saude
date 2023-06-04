package br.org.ici.saude.geradorcodigo.entidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

class TypeModelTest {

  @Test
  void addValoresTest() {
    TypeModel type = new TypeModel("Sexo", "br.com.saude.entity");
    type.addValores(new String[] {"1;MASCULINO;Masculino", "2;FEMININO;Feminino"});
    List<ValorType> valores = type.getValores();
    assertEquals(1, valores.get(0).getId());
    assertEquals("MASCULINO", valores.get(0).getNome());
    assertEquals("Masculino", valores.get(0).getDescricao());

    assertEquals(2, valores.get(1).getId());
    assertEquals("FEMININO", valores.get(1).getNome());
    assertEquals("Feminino", valores.get(1).getDescricao());
  }

}
