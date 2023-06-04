package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.List;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.entidade.EntidadeModel;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;
import lombok.Data;

@Data
public class EntidadeArquivo {
  private String pacote;
  private String path;
  private String nome;
  private String mensagem;
  private boolean gerarAuditoria;
  private List<String> metodos;
  private List<AtributoArquivo> atributos;



  public EntidadeModel toEntidadeModel() {
    EntidadeModel entidade = new EntidadeModel(this.getNome(), this.getPacote());
    entidade.addAtributos(getAtributosModel());
    return entidade;
  }

  private String getNomeVariavel() {
    return this.nome.substring(0, 1).toLowerCase() + this.nome.substring(1, this.nome.length());
  }

  private List<AtributosModel> getAtributosModel() {
    return this.atributos.stream().map(atributoArq -> {
      AtributosModel atributo = new AtributosModel(atributoArq.getNome(), atributoArq.getMensagem(),
          atributoArq.getTipo(), atributoArq.getAnotacoes());
      if (atributoArq.existeMapeamento()) {
        atributo.setNomeVariavel(getNomeVariavel());
        atributo.addMapeamento(
            GeradorImports.get(atributoArq.getMapeamento().getTipoMapeamento()).getAnotacao(),
            atributoArq.getMapeamento().isJoinColumn(), atributoArq.getMapeamento().getCascade());
      }
      return atributo;
    }).toList();
  }

  public List<AtributoArquivo> getTypes() {
    return this.atributos.stream().filter(atr -> atr.isEnum()).toList();
  }

  public boolean existePesquisa() {
    return this.metodos.stream().anyMatch(met -> met.toUpperCase().contains("pesquisa"));
  }

  public List<String> getColunasPesquisa() {
    return this.atributos.stream().filter(atr -> atr.isPesquisa()).map(atr -> atr.getNome())
        .toList();
  }

  public String getQueryPesquisa() {
    String queryAnd =
        this.atributos.stream().filter(atr -> atr.isPesquisa()).map(atr -> atr.getNome()).reduce("",
            (query, coluna) -> query + " and e." + coluna + " = :" + coluna + "\n");

    StringBuilder query = new StringBuilder();
    query.append(" from ").append(this.nome + "Entity e").append(" where  ").append(queryAnd);

    return query.toString();
  }
}
