package br.org.ici.saude.geradorcodigo.service;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;


public class GeradorService implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existeMetodos())
        .map(entidadeArq -> new ServiceModel(entidadeArq.getNome(), entidadeArq.getPacote(),
            entidadeArq.getMensagem(), entidadeArq.getMetodos()))
        .toList();
  }

}
