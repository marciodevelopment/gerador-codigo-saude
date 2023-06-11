package br.org.ici.saude.geradorcodigo.geradores;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorBuilder implements GeradorArquivo {

  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.toEntidadeModel().getExisteBuilderConstrutor().booleanValue())
        .map(EntidadeArquivo::toEntidadeModel).toList();
  }


}
