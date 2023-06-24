package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.List;
import lombok.Data;

@Data
public class ArquivoConfiguracao {
  private String diretorioProjetoJava;
  private String diretorioProjetoAngular;
  private String pacoteProjeto;
  private List<EntidadeArquivo> entidades;
}
