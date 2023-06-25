package br.org.ici.saude.geradorcodigo.angular.geradores;

import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.models.TypeAngularModel;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorAngularArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorAngularType implements GeradorAngularArquivo {
  @Override
  public List<? extends ArquivoAngularModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream()
        .map(entidadeArq -> entidadeArq.getTypes().stream()
            .map(typeArq -> new TypeAngularModel(typeArq.getTipo().replace("Type", ""),
                entidadeArq.getPacote(), arquivoConfiguracao.getPacoteProjeto(), typeArq.getType()))
            .toList())
        .flatMap(List::stream).toList();

  }

}


