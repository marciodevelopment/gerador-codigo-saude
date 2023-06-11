package br.org.ici.saude.geradorcodigo.entidade;

import br.org.ici.saude.geradorcodigo.common.ArquivoType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;

@Getter
public class PesquisaViewModel extends BaseModel {
  public PesquisaViewModel(String nome, String pacote) {
    super(nome, pacote, ArquivoType.VIEW_PESQUISA.getPacoteArquivo(pacote));
  }

}
