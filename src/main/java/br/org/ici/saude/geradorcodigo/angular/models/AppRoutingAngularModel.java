package br.org.ici.saude.geradorcodigo.angular.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class AppRoutingAngularModel {
  private final String nomeVariavel;
  private final String nome;
}
