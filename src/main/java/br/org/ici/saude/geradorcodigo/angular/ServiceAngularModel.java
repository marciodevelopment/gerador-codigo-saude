package br.org.ici.saude.geradorcodigo.angular;

import java.util.List;
import br.org.ici.saude.geradorcodigo.common.ArquivoAngularType;
import br.org.ici.saude.geradorcodigo.service.ServiceModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceAngularModel extends ServiceModel {

  private String path;


  public ServiceAngularModel(String nome, String pacoteProjeto, String pacote, String path,
      List<String> metodos) {
    super(nome, pacote, ArquivoAngularType.SERVICE.getPacoteArquivo(pacoteProjeto, pacote));
    this.path = path;

  }


}
