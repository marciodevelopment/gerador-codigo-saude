package br.org.ici.saude.geradorcodigo.angular.geradores;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.angular.models.ModuleAngularModel;
import br.org.ici.saude.geradorcodigo.angular.models.ModuleRoutingModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;


public class GeradorModuleAngular implements GeradorAngularArquivo {

  @Override
  public List<? extends ArquivoAngularModel> converterParaArquivoModel(
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
      return new ModuleAngularModel(arquivoConfiguracao.getPacoteProjeto(), pacoteEntidade,
          entry.getValue());
    }).toList();
  }

}
