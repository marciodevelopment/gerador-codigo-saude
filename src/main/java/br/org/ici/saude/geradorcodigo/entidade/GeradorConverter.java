package br.org.ici.saude.geradorcodigo.entidade;

import java.util.ArrayList;
import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorConverter implements GeradorArquivo {


  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    List<ArquivoModel> arquivos = new ArrayList<>();
    for (EntidadeArquivo entidadeArq : arquivoConfiguracao.getEntidades()) {
      arquivos.addAll(entidadeArq.getTypes().stream()
          .map(typeArq -> new TypeModel(typeArq.getTipo().replace("Type", ""),
              entidadeArq.getPacote(), typeArq.getType()))
          .toList());
    }
    return arquivos;
  }



}
