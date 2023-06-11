package br.org.ici.saude.geradorcodigo.web;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;

public class ControllerTestModel extends BaseModel {

  private Boolean existePost = false;
  private Boolean existePut = false;
  private Boolean existeGet = false;
  private Boolean existeDelete = false;
  private Boolean existePesquisa = false;
  @Getter
  private String path;

  public ControllerTestModel(String nome, String pacote) {
    super(nome, pacote, ArquivoType.CONTROLLER_TESTE.getPacoteArquivo(pacote));
  }

  public ControllerTestModel(String nome, String pacote, List<String> metodos, String path) {
    super(nome, pacote, ArquivoType.CONTROLLER_TESTE.getPacoteArquivo(pacote));
    if (metodos == null)
      return;
    this.path = path;
    this.existePost = metodos.toString().toLowerCase().contains("post");
    this.existePut = metodos.toString().toLowerCase().contains("put");
    this.existeGet = metodos.toString().toLowerCase().contains("get");
    this.existeDelete = metodos.toString().toLowerCase().contains("delete");
    this.existePesquisa = metodos.toString().toLowerCase().contains("pesquisa");
  }

  public Boolean getGerarAtualizar() {
    return this.existePut;
  }

  public Boolean getGerarSalvar() {
    return this.existePost;
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


}
