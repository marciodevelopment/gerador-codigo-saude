package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.entidade.AnotacaoModel;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.imports.AnotacaoImport;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;

public class FiltroAtributos {

  private ArquivoConfiguracao arquivoConfiguracao;

  public FiltroAtributos(ArquivoConfiguracao arquivoConfiguracao) {
    this.arquivoConfiguracao = arquivoConfiguracao;
  }


  public List<AtributosModel> getAtributosDesnormalizadosModel(String nomeEntidade,
      MetodoType metodo) {
    List<AtributosModel> atributos = this.getAtributosDesnormalizados(nomeEntidade, metodo).stream()
        .map(atr -> atr.toAtributoDesnormalizadoModel()).toList();

    atributos.forEach(
        atributo -> atributo.getAnotacoes().removeIf(ant -> ant.isGetter() || ant.isConverter()));

    return atributos;
  }


  public List<AtributosModel> getAtributosDesnormalizadosParaRequest(String nomeEntidade,
      String mensagem, MetodoType metodo) {
    List<AtributosModel> atributos = this.getAtributosDesnormalizados(nomeEntidade, metodo).stream()
        .map(atr -> atr.toAtributoDesnormalizadoComAnotacoesModel()).toList();

    atributos.forEach(atr -> {
      List<AnotacaoModel> anotacoesFiltradas =
          atr.getAnotacoes().stream().filter(ant -> !ant.isGetter() && !ant.isConverter()).toList();
      atr.setAnatcoes(anotacoesFiltradas);
    });

    AnotacaoImport importAnotacao = GeradorImports.get("notnull");
    AnotacaoModel notNullAnotacao = new AnotacaoModel(importAnotacao.getAnotacao(),
        importAnotacao.getNomeImport(), mensagem, null);

    atributos.forEach(atributo -> {
      if (atributo.isId(nomeEntidade) && atributo.isNotNull()) {
        atributo.addAnotacao(notNullAnotacao);
      }
    });

    return atributos;
  }


  public Collection<AtributoArquivo> getAtributosDesnormalizados(String nomeEntidade,
      MetodoType metodo, int nivel) {
    if (nivel > 1)
      return new ArrayList<>();
    EntidadeArquivo entidadeParaDesnormalizacao = arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.getNome().equals(nomeEntidade)).findFirst().orElseThrow();

    List<AtributoArquivo> atributos = entidadeParaDesnormalizacao.getAtributos().stream()
        .filter(ent -> ent.existeMetodo(metodo)).toList();

    List<AtributoArquivo> atributosDeEntidade =
        atributos.stream().filter(AtributoArquivo::isEntity).toList();


    List<AtributoArquivo> atributosSemCascade = atributosDeEntidade.stream()
        .filter(atributo -> !atributo.isCascade() && atributo.existeMetodo(metodo)).toList();

    atributos = atributos.stream().filter(atr -> !atr.isEntity()).toList();

    List<EntidadeArquivo> entidadesParaDesnomalizacao = arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> atributosDeEntidade.stream().anyMatch(atrEntidade -> atrEntidade.isCascade()
            && atrEntidade.getTipo().contains(ent.getNome())))
        .toList();

    List<AtributoArquivo> atributosDesnormalizados = new ArrayList<>();
    atributosDesnormalizados.addAll(atributos);

    atributosDesnormalizados
        .addAll(atributosSemCascade.stream().map(atr -> new AtributoArquivo(atr.getNomeAtributoId(),
            atr.getMensagem(), "Integer", List.of(atr.isNotNull() ? "notnull" : null))).toList());


    atributosDesnormalizados.addAll(entidadesParaDesnomalizacao.stream()
        .map(ent -> getAtributosDesnormalizados(ent.getNome(), metodo)).flatMap(Collection::stream)
        .distinct().toList());

    List<AtributoArquivo> atributosDesnormalizadosSemCascade = atributosDesnormalizados.stream()
        .filter(atr -> atributosSemCascade.stream().anyMatch(atrSemCascade -> atr.getNome()
            .toLowerCase().contains(atrSemCascade.getNome().toLowerCase())))
        .toList();

    atributosDesnormalizadosSemCascade.forEach(atr -> {
      atr.setSemCascadeDesnomalizado(true);
      String nomeClasseOrigem = atributosSemCascade.stream().filter(atrSemCascade -> atr.getNome()
          .toLowerCase().contains(atrSemCascade.getNome().toLowerCase())).findAny().get().getTipo();
      atr.setTipoOrigem(nomeClasseOrigem);
    });

    return atributosDesnormalizados;

  }

  public Collection<AtributoArquivo> getAtributosDesnormalizados(String nomeEntidade,
      MetodoType metodo) {
    return this.getAtributosDesnormalizados(nomeEntidade, metodo, 0);
  }

}
