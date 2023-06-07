


package br.org.ici.saude.endereco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Cidade")
public class CidadeEntity {

  @Id
  @SequenceGenerator(name = "Cidade", sequenceName = "Cidade_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Cidade")
  private Integer cdCidade;

  @NotEmpty(message = "Nome Cidade")
  private String nmCidade;

  public CidadeEntity(@NotEmpty String nmCidade) {
    super();
    this.nmCidade = nmCidade;
  }


}