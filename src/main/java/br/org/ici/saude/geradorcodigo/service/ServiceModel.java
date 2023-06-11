package br.org.ici.saude.geradorcodigo.service;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
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
    super(nome, pacote, ArquivoType.SERVICE.getPacoteArquivo(pacote));
    this.mensagem = mensagem;
  }

  public ServiceModel(String nome, String pacote, String mensagem, List<String> metodos) {
    super(nome, pacote, ArquivoType.ENTITY.getPacoteArquivo(pacote));
    this.mensagem = mensagem;
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
}
