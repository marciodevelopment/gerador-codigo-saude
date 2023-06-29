package br.org.ici.saude.geradorcodigo.web;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorMapper implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(ent -> ent.existeMetodos())
        .map(entidadeArq -> {

          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);

          List<AtributosModel> atributosUpdate =
              filtroAtributos.getAtributosDesnormalizadosParaRequest(entidadeArq.getNome(),
                  entidadeArq.getMensagem(), MetodoType.PUT);

          List<AtributosModel> atributosNovo =
              filtroAtributos.getAtributosDesnormalizadosParaRequest(entidadeArq.getNome(),
                  entidadeArq.getMensagem(), MetodoType.POST);

          return new MapperModel(entidadeArq.getNome(), entidadeArq.getPacote(),
              entidadeArq.getMetodos(), atributosNovo, atributosUpdate);
        }).toList();

  }
}
