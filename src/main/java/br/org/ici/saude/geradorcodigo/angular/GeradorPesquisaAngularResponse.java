package br.org.ici.saude.geradorcodigo.angular;

import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorPesquisaAngularResponse implements GeradorArquivo {

  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existePesquisa)
        .map(entidadeArq -> {
          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
          Collection<AtributosModel> atributosFiltratos = filtroAtributos
              .getAtributosDesnormalizadosModel(entidadeArq.getNome(), MetodoType.PESQUISA);

          Collection<? extends AtributosModel> atributos =
              atributosFiltratos.stream().map(atr -> new AtributosAngularModel(atr.getNome(),
                  atr.getMensagem(), atr.getTipo(), atr.getAnotacoes())).toList();

          return new InterfaceAngularModel(ArquivoAngularType.PESQUISA_RESPONSE,
              entidadeArq.getNome(), arquivoConfiguracao.getPacoteProjeto(),
              entidadeArq.getPacote(), atributos);
        }).toList();
  }
}
