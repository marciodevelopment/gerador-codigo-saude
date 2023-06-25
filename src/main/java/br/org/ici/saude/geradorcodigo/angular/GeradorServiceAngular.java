package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.models.ServiceAngularModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;


public class GeradorServiceAngular implements GeradorAngularArquivo {

  @Override
  public List<? extends ArquivoAngularModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {

    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existeMetodos())
        .map(entidadeArq -> new ServiceAngularModel(entidadeArq.getNome(),
            arquivoConfiguracao.getPacoteProjeto(), entidadeArq.getPacote(), entidadeArq.getPath(),
            entidadeArq.getMetodos()))
        .toList();
  }

}
