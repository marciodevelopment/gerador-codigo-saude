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

  public String getNomeModuloImportacao() {
    if (!super.getSemCascadeDesnomalizado().booleanValue()) {
      return null;
    }
    String nomeModulo =
        super.getTipoOrigem().substring(1, super.getTipoOrigem().indexOf("entity") - 1);
    nomeModulo = nomeModulo.substring(nomeModulo.lastIndexOf(".") + 1, nomeModulo.length());
    nomeModulo =
        nomeModulo.substring(0, 1).toUpperCase() + nomeModulo.substring(1, nomeModulo.length());
    return nomeModulo + "Module";
  }

  public String getPacoteModuloImportacao() {
    if (!super.getSemCascadeDesnomalizado().booleanValue()) {
      return null;
    }
    String nomeModulo =
        super.getTipoOrigem().substring(1, super.getTipoOrigem().indexOf("entity") - 1);
    return nomeModulo.substring(nomeModulo.lastIndexOf(".") + 1, nomeModulo.length());
  }

}
