package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.List;
import br.org.ici.saude.geradorcodigo.angular.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.common.BaseAngularModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceAngularModel extends BaseAngularModel {

  private String path;
  private Boolean existePost = false;
  private Boolean existePut = false;
  private Boolean existeGet = false;
  private Boolean existeDelete = false;
  private Boolean existePesquisa = false;


  public ServiceAngularModel(String nome, String pacoteProjeto, String pacote, String path,
      List<String> metodos) {
    super(nome, ArquivoAngularType.SERVICE.caminhoArquivo(pacoteProjeto, pacote));
    this.path = path;
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
