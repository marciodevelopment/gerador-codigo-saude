package br.org.ici.saude.geradorcodigo.web;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoJavaType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;

public class MapperModel extends BaseModel {

  private Boolean existePost = false;
  private Boolean existePut = false;
  private Boolean existeGet = false;
  private Boolean existeDelete = false;
  private Boolean existePesquisa = false;

  public MapperModel(String nome, String pacote, List<String> metodos) {
    super(nome, pacote, ArquivoJavaType.MAPPER.getPacoteArquivo(pacote));
    if (metodos == null)
      return;
    this.existePost = metodos.toString().toLowerCase().contains("post");
    this.existePut = metodos.toString().toLowerCase().contains("put");
    this.existeGet = metodos.toString().toLowerCase().contains("get");
    this.existeDelete = metodos.toString().toLowerCase().contains("delete");
    this.existePesquisa = metodos.toString().toLowerCase().contains("pesquisa");
  }

  public Boolean getGerarSalvar() {
    return this.existePost || this.existePut;
  }

  public Boolean getGerarDelete() {
    return this.existeDelete;
  }

  public Boolean getGerarBuscarPorId() {
    return this.existePut || this.existeGet;
  }

  public Boolean getGerarPesquisar() {
    return this.existePesquisa;
  }

  public Boolean getGerarAtualizar() {
    return this.existePut;
  }


}
