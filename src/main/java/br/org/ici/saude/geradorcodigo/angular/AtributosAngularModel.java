package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.entidade.AnotacaoModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter

public class AtributosAngularModel extends AtributosModel {
  public AtributosAngularModel(String nome, String mensagem, String tipo,
      List<AnotacaoModel> anotacoes) {
    super(nome, mensagem, tipo, anotacoes);
  }


  @Override
  public String getTipo() {
    String tipoAtributo = super.getTipo();
    if (tipoAtributo.equals("Integer") || tipoAtributo.equals("Double")) {
      return "number";
    }
    if (tipoAtributo.equals("LocalDate") || tipoAtributo.equals("LocalDateTime")) {
      return "Date";
    }
    if (tipoAtributo.equals("String")) {
      return "string";
    }
    return tipoAtributo;
  }

}
