package br.org.ici.saude.geradorcodigo.imports;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnotacaoImport {
  private final String classe;
  private final String anotacao;
  private final String nomeImport;
}
