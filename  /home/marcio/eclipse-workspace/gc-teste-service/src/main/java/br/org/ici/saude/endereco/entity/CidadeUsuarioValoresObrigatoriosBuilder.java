package br.org.ici.saude.endereco.entity;

import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotEmpty;

@Accessors(chain = true, fluent = true)
@Data
public class CidadeValoresObrigatoriosBuilder {
  @NotEmpty 
  private String nmCidade;

  public CidadeEntity build() {
    return new CidadeEntity(this);
  }
}
