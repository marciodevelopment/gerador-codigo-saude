package br.org.ici.saude.geradorcodigo.configuracao;

import java.util.List;
import lombok.Data;

@Data
public class ArquivoConfiguracao {
  private String diretorioProjeto;
  private List<EntidadeArquivo> entidades;
}
