package br.org.ici.saude.geradorcodigo.geradores;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;

public interface GeradorAngularArquivo {
  List<? extends ArquivoAngularModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao);
}
