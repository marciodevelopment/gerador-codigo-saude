package br.org.ici.saude.usuario.entity;

import lombok.Builder;
import lombok.Getter;

import lombok.Getter;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.NotNull;

@Accessors(chain = true, fluent = true)
@Data
public class DocumentoCpfValoresObrigatoriosBuilder {
  @NotNull 
  private Integer cdDocumentoCpf;
  @NotEmpty 
  private String nrCpf;
  @NotEmpty 
  private LocalDate dtEmissao;

  public DocumentoCpfEntity build() {
    return new DocumentoCpfEntity(this);
  }
}
