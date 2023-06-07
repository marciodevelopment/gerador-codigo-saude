package br.org.ici.saude.usuario.entity;

import lombok.Builder;
import lombok.Getter;

import lombok.Getter;
import java.time.LocalDate;
import br.org.ici.saude.usuario.type.SexoType;
import jakarta.validation.constraints.NotEmpty;
import br.org.ici.saude.usuario.type.SexoConverter;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Convert;
import jakarta.persistence.OneToOne;

@Accessors(chain = true, fluent = true)
@Data
public class UsuarioValoresObrigatoriosBuilder {
  @NotEmpty 
  private String nmUsuario;
  @NotEmpty 
  private String nmMae;
  @NotEmpty 
  private String nmPai;
  @NotNull 
  private String nmSocial;
  @NotNull 
  private SexoType sexo;
  @NotNull 
  private LocalDate dtNascimento;
  @NotNull 
  private DocumentoEntity documentoCpf;
  @NotNull 
  private CidadeEntity cidadeNascimento;

  public UsuarioEntity build() {
    return new UsuarioEntity(this);
  }
}
