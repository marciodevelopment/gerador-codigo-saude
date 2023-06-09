package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;

public class FiltroAtributos {

  private ArquivoConfiguracao arquivoConfiguracao;

  public FiltroAtributos(ArquivoConfiguracao arquivoConfiguracao) {
    this.arquivoConfiguracao = arquivoConfiguracao;
  }


  public List<AtributosModel> getAtributosDesnormalizadosModel(String nomeEntidade,
      MetodoType metodo) {
    return this.getAtributosDesnormalizados(nomeEntidade, metodo).stream()
        .map(atr -> atr.toAtributoDesnormalizadoModel()).toList();
  }

  public Collection<AtributoArquivo> getAtributosDesnormalizados(String nomeEntidade,
      MetodoType metodo) {
    EntidadeArquivo entidadeParaDesnormalizacao = arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.getNome().equals(nomeEntidade)).findFirst().orElseThrow();

    List<AtributoArquivo> atributos = entidadeParaDesnormalizacao.getAtributos().stream()
        .filter(ent -> ent.existeMetodo(metodo)).toList();

    List<AtributoArquivo> atributosDeEntidade =
        atributos.stream().filter(AtributoArquivo::isEntity).toList();

    atributos = atributos.stream().filter(atr -> !atr.isEntity()).toList();

    List<EntidadeArquivo> entidades = arquivoConfiguracao.getEntidades().stream().filter(ent -> {
      return atributosDeEntidade.stream().anyMatch(atrEntidade -> {
        return atrEntidade.getTipo().contains(ent.getNome());
      });
    }).toList();

    List<AtributoArquivo> entidadesDesnormalizadas = new ArrayList<>();
    entidadesDesnormalizadas.addAll(atributos);
    entidadesDesnormalizadas
        .addAll(entidades.stream().map(ent -> getAtributosDesnormalizados(ent.getNome(), metodo))
            .flatMap(Collection::stream).distinct().toList());

    return entidadesDesnormalizadas;

  }

}
