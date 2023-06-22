package br.org.ici.saude.geradorcodigo.entidade.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorAngularType implements GeradorArquivo {
  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    /*
     * return arquivoConfiguracao.getEntidades().stream() .map(entidadeArq ->
     * entidadeArq.getTypes().stream() .map(typeArq -> new
     * TypeModel(typeArq.getTipo().replace("Type", ""), entidadeArq.getPacote(), typeArq.getType()))
     * .toList()) .flatMap(List::stream).toList();
     */
    return null;

  }

}
