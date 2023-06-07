


package br.org.ici.saude.usuario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Getter;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.NotNull;

@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "DocumentoCpf")
public class DocumentoCpfEntity {

  @Id
  @SequenceGenerator(name = "DocumentoCpf", sequenceName = "DocumentoCpf_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocumentoCpf")
  private Integer cdDocumentoCpf;

  @NotNull(message = "Código Documento CPF")
  @Getter()
  private Integer cdDocumentoCpf;

  @NotEmpty(message = "Número CPF")
  @cpf(message = "Número CPF")
  @Size(message = "Número CPF", max=255)
  private String nrCpf;

  @NotEmpty(message = "Data emissão")
  @PastOrPresent(message = "Data emissão")
  private LocalDate dtEmissao;

  public DocumentoCpfEntity(@NotNull Integer cdDocumentoCpf, @NotEmpty String nrCpf, @NotEmpty LocalDate dtEmissao) {
    super();
    this.cdDocumentoCpf = cdDocumentoCpf;
    this.nrCpf = nrCpf;
    this.dtEmissao = dtEmissao;
  }


}