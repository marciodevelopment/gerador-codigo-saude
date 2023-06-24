package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;

public class GeradorNovoAngularRequest implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existeNovo)
        .map(entidadeArq -> {
          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
          List<? extends AtributosModel> atributos = filtroAtributos
              .getAtributosDesnormalizadosParaRequest(entidadeArq.getNome(),
                  entidadeArq.getMensagem(), MetodoType.POST)
              .stream().map(atr -> new AtributosAngularModel(atr.getNome(), atr.getMensagem(),
                  atr.getTipo(), atr.getAnotacoes()))
              .toList();
          return new InterfaceAngularModel(ArquivoAngularType.NOVO_REQUEST, entidadeArq.getNome(),
              arquivoConfiguracao.getPacoteProjeto(), entidadeArq.getPacote(), atributos);
        }).toList();
  }

}
