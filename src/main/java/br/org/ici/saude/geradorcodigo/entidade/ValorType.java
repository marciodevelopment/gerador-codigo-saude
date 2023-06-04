package br.org.ici.saude.geradorcodigo.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValorType {
  private Integer id;
  private String nome;
  private String descricao;


  public static ValorType of(String valor) {
    return new ValorType(Integer.valueOf(valor.split(";")[0]), valor.split(";")[1],
        valor.split(";")[2]);
  }
}
