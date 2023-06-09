package br.org.ici.saude.geradorcodigo.configuracao;

import lombok.Getter;

public enum MetodoType {
  PESQUISA("pesquisa"), GET("get"), POST("post"), PUT("put"), DELETE("delete");

  @Getter
  private String nome;

  MetodoType(String nome) {
    this.nome = nome;
  }
}
