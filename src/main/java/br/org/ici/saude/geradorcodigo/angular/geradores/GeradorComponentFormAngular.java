package br.org.ici.saude.geradorcodigo.angular.geradores;

import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.AtributosAngularModel;
import br.org.ici.saude.geradorcodigo.angular.models.ComponentFormModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorComponentFormAngular implements GeradorAngularArquivo {

  public List<? extends ArquivoAngularModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existeNovo)
        .map(entidadeArq -> {
          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
          Collection<? extends AtributosModel> atributosFiltratos = filtroAtributos
              .getAtributosDesnormalizadosParaRequest(entidadeArq.getNome(),
                  entidadeArq.getMensagem(), MetodoType.POST)
              .stream().map(atr -> new AtributosAngularModel(atr.getNome(), atr.getMensagem(),
                  atr.getTipo(), atr.getAnotacoes()))
              .toList();
          return new ComponentFormModel(entidadeArq.getNome(),
              arquivoConfiguracao.getPacoteProjeto(), entidadeArq.getPacote(), atributosFiltratos,
              entidadeArq.getMensagem());
        }).toList();
  }
}
