package br.org.ici.saude.geradorcodigo.repositorio;

import br.org.ici.saude.geradorcodigo.common.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceModel extends BaseModel {
  private Boolean existePost = false;
  private Boolean existePut = false;
  private Boolean existeGet = false;
  private Boolean existeDelete = false;
  private Boolean existePesquisa = false;
  private String mensagem;


  public ServiceModel(String nome, String pacote, String mensagem) {
    super(nome, pacote);
    this.mensagem = mensagem;
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



}
