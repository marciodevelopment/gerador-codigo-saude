package br.org.ici.saude.geradorcodigo.repositorio;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorRepository implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existeMetodos())
        .map(entidadeArq -> new RepositorioModel(entidadeArq.getNome(), entidadeArq.getPacote(),
            entidadeArq.existePesquisa(), entidadeArq.getColunasPesquisa(),
            entidadeArq.getQueryPesquisa()))
        .toList();
  }
}
