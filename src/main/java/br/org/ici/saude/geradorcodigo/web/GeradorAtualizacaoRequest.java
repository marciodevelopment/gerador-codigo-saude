package br.org.ici.saude.geradorcodigo.web;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.configuracao.FiltroAtributos;
import br.org.ici.saude.geradorcodigo.configuracao.MetodoType;
import br.org.ici.saude.geradorcodigo.entidade.AtributosModel;
import br.org.ici.saude.geradorcodigo.geradores.GeradorArquivo;

public class GeradorAtualizacaoRequest implements GeradorArquivo {

  @Override
  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    return arquivoConfiguracao.getEntidades().stream().filter(EntidadeArquivo::existeAtualizacao)
        .map(entidadeArq -> {
          FiltroAtributos filtroAtributos = new FiltroAtributos(arquivoConfiguracao);
          List<AtributosModel> atributos = filtroAtributos.getAtributosDesnormalizadosParaRequest(
              entidadeArq.getNome(), entidadeArq.getMensagem(), MetodoType.PUT);
          return new ResponseRequestModel(entidadeArq.getNome(), entidadeArq.getPacote(),
              atributos);
        }).toList();
  }

}
