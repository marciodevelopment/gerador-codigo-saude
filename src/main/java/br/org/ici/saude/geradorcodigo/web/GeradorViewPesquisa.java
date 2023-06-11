package br.org.ici.saude.geradorcodigo.web;

import java.util.Collection;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.entidade.PesquisaViewModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorViewPesquisa implements GeradorArquivo {
  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existePesquisa())
        .map(entidadeArq -> {
          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
          Collection<AtributosModel> atributosFiltratos = filtroAtributos
              .getAtributosDesnormalizadosModel(entidadeArq.getNome(), MetodoType.PESQUISA);

          PesquisaViewModel pesquisaView =
              new PesquisaViewModel(entidadeArq.getNome(), entidadeArq.getPacote());
          pesquisaView.addAtributos(atributosFiltratos);
          return pesquisaView;
        }).toList();
  }
}
