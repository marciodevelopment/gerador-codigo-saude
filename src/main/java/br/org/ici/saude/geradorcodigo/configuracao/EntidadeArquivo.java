package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.Collection;
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



  public EntidadeModel toEntidadeModelView() {
    EntidadeModel entidade = new EntidadeModel(this.getNome(), this.getPacote());
    entidade.addAtributos(getAtributosPesquisa());
    return entidade;
  }

  public EntidadeModel toEntidadeModel() {
    EntidadeModel entidade = new EntidadeModel(this.getNome(), this.getPacote());
    entidade.addAtributos(getAtributosModel());
    return entidade;
  }

  private String getNomeVariavel() {
    return this.nome.substring(0, 1).toLowerCase() + this.nome.substring(1, this.nome.length());
  }

  private List<AtributosModel> getAtributosPesquisa() {
    return this.atributos.stream().filter(atr -> atr.isPesquisa()).map(atributoArq -> {
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

  public boolean existeGet() {
    if (this.metodos == null)
      return false;
    return this.metodos.stream().anyMatch(met -> met.toLowerCase().contains("get"));
  }

  public boolean existePesquisa() {
    if (this.metodos == null)
      return false;
    return this.metodos.stream().anyMatch(met -> met.toLowerCase().contains("pesquisa"));
  }

  public boolean existeDelete() {
    if (this.metodos == null)
      return false;
    return this.metodos.stream().anyMatch(met -> met.toLowerCase().contains("delete"));
  }

  public boolean existeAtualizacao() {
    if (this.metodos == null)
      return false;
    return this.metodos.stream().anyMatch(met -> met.toLowerCase().contains("put"));
  }

  public boolean existeNovo() {
    if (this.metodos == null)
      return false;
    return this.metodos.stream().anyMatch(met -> met.toLowerCase().contains("post"));
  }

  public List<String> getColunasPesquisa() {
    return this.atributos.stream().filter(atr -> atr.isPesquisa()).map(atr -> atr.getNome())
        .toList();
  }

  public String getQueryPesquisa() {
    Collection<AtributoArquivo> atributosPesquisa =
        this.atributos.stream().filter(atr -> atr.isPesquisa()).toList();
    StringBuilder queryAnd = new StringBuilder();
    for (AtributoArquivo atr : atributosPesquisa) {
      if (atr.isString()) {
        queryAnd.append("\n" + atr.getNome() + " like :" + atr.getNome());
      } else {
        queryAnd.append("\n" + atr.getNome() + " = :" + atr.getNome());
      }
    }
    StringBuilder query = new StringBuilder();
    query.append("\nfrom ").append(this.nome + "Entity e\n").append("where  1 = 1")
        .append(queryAnd);

    return query.toString();
  }

  public boolean existeMetodos() {
    return metodos != null && !metodos.isEmpty();
  }

  public boolean existePath() {
    return this.path != null && !this.path.isBlank();
  }
}
