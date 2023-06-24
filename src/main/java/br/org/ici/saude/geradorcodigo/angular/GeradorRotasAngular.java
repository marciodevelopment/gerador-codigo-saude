package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;


public class GeradorRotasAngular implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {

    Map<String, List<ModuleRoutingModel>> rotas =
        arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existePath)
            .map(entidade -> new ModuleRoutingModel(entidade.getNome(),
                entidade.getPacote().replace(arquivoConfiguracao.getPacoteProjeto() + ".", ""),
                entidade.existePesquisa(), entidade.existeAtualizacao(), entidade.existeNovo(),
                entidade.getPath(),
                entidade.getPacote().replace(arquivoConfiguracao.getPacoteProjeto() + ".", "")
                    .toUpperCase(),
                entidade.getPacote()))
            .collect(Collectors.groupingBy(ModuleRoutingModel::getNomeModulo));
    return rotas.entrySet().stream().map(entry -> {
      String pacoteEntidade = entry.getValue().get(0).getPacoteEntidade();
      String nomeRota = entry.getValue().get(0).getNomeRota();
      return new RoutingModel(arquivoConfiguracao.getPacoteProjeto(), pacoteEntidade,
          entry.getValue(), nomeRota);
    }).toList();

  }

}
