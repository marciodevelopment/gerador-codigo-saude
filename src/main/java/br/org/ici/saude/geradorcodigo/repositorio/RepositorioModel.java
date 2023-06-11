package br.org.ici.saude.geradorcodigo.repositorio;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RepositorioModel extends BaseModel {
  private boolean existePesquisa;
  private String query;
  private List<String> colunasQuery = new ArrayList<>();

  public RepositorioModel(String nome, String pacote) {
    super(nome, pacote, ArquivoType.REPOSITORY.getPacoteArquivo(pacote));
  }

  public RepositorioModel(String nome, String pacote, boolean existePesquisa,
      List<String> colunasQuery, String query) {
    super(nome, pacote, ArquivoType.ENTITY.getPacoteArquivo(pacote));
    this.existePesquisa = existePesquisa;
    this.colunasQuery = colunasQuery;
    this.query = query;
  }

  public RepositorioModel addColunaQuery(String... coluna) {
    this.colunasQuery.addAll(List.of(coluna));
    return this;
  }
}
