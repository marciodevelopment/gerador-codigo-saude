package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;


public class GeradorServiceAngular implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {

    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existeMetodos())
        .map(entidadeArq -> new ServiceAngularModel(entidadeArq.getNome(),
            arquivoConfiguracao.getPacoteProjeto(), entidadeArq.getPacote(), entidadeArq.getPath(),
            entidadeArq.getMetodos()))
        .toList();
  }

}
