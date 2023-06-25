package br.org.ici.saude.geradorcodigo.angular.geradores;

import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.AtributosAngularModel;
import br.org.ici.saude.geradorcodigo.angular.models.PesquisaComponentFormModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorPesquisaComponentFormAngular implements GeradorAngularArquivo {

  public List<? extends ArquivoAngularModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existePesquisa)
        .map(entidadeArq -> {
          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
          Collection<? extends AtributosModel> atributosFiltratos = filtroAtributos
              .getAtributosDesnormalizadosModel(entidadeArq.getNome(), MetodoType.PESQUISA).stream()
              .map(atr -> new AtributosAngularModel(atr.getNome(), atr.getMensagem(), atr.getTipo(),
                  atr.getAnotacoes()))
              .toList();
          return new PesquisaComponentFormModel(entidadeArq.getNome(),
              arquivoConfiguracao.getPacoteProjeto(), entidadeArq.getPacote(), atributosFiltratos,
              entidadeArq.getMensagem(), entidadeArq.existeNovo(), entidadeArq.existeAtualizacao(),
              entidadeArq.existeDelete());
        }).toList();
  }
}
