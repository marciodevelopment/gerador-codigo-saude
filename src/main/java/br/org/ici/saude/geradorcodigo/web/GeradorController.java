package br.org.ici.saude.geradorcodigo.web;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;

public class GeradorController implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {

    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existeMetodos())
        .map(entidadeArq -> new ControllerModel(entidadeArq.getNome(), entidadeArq.getPacote(),
            entidadeArq.getMetodos(), entidadeArq.getPath()))
        .toList();
  }

}
