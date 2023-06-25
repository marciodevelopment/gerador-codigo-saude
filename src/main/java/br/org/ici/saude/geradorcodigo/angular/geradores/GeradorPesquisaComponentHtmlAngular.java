package br.org.ici.saude.geradorcodigo.angular.geradores;

import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.models.PesquisaComponentHtmlModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorPesquisaComponentHtmlAngular implements GeradorAngularArquivo {

  public List<? extends ArquivoAngularModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existePesquisa)
        .map(entidadeArq -> new PesquisaComponentHtmlModel(entidadeArq.getNome(),
            arquivoConfiguracao.getPacoteProjeto(), entidadeArq.getPacote()))
        .toList();
  }
}
