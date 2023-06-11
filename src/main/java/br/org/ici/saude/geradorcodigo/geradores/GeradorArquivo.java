package br.org.ici.saude.geradorcodigo.geradores;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;

public interface GeradorArquivo {
  List<? extends ArquivoModel> converterParaArquivoModel(ArquivoConfiguracao arquivoConfiguracao);
}
