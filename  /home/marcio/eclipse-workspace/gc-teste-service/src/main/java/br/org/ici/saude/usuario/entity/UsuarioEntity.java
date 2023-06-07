


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
import java.time.LocalDate;
import br.org.ici.saude.usuario.type.SexoType;
import jakarta.validation.constraints.NotEmpty;
import br.org.ici.saude.usuario.type.SexoConverter;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Convert;
import jakarta.persistence.OneToOne;

@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Usuario")
public class UsuarioEntity {

  @Id
  @SequenceGenerator(name = "Usuario", sequenceName = "Usuario_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario")
  private Integer cdUsuario;

  @NotEmpty(message = "Nome usuário")
  @min(message = "Nome usuário", size=10)
  @Getter()
  private String nmUsuario;

  @NotEmpty(message = "Nome mãe")
  @min(message = "Nome mãe", size=10)
  @Getter()
  private String nmMae;

  @NotEmpty(message = "Nome pai")
  @min(message = "Nome pai", size=10)
  @Getter()
  private String nmPai;

  @NotNull(message = "Nome Social")
  @Getter()
  private String nmSocial;

  @NotNull(message = "Sexo")
  @Convert(converter = SexoConverter.class)
  @Getter()
  private SexoType sexo;

  @NotNull(message = "Data de nascimento")
  @Getter()
  private LocalDate dtNascimento;

  @NotNull(message = "Documento CPF")
  @Getter()
  @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)	
  private DocumentoEntity documentoCpf;

  @NotNull(message = "Cidade Nascimento")
  @Getter()
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cdCidade", nullable = false)	
  private CidadeEntity cidadeNascimento;

  public UsuarioEntity(@NotNull UsuarioNovoBuilder usuarioNovoBuider) {
    super();
    this.nmUsuario = usuarioNovaBuilder.nmUsuario();
    this.nmMae = usuarioNovaBuilder.nmMae();
    this.nmPai = usuarioNovaBuilder.nmPai();
    this.nmSocial = usuarioNovaBuilder.nmSocial();
    this.sexo = usuarioNovaBuilder.sexo();
    this.dtNascimento = usuarioNovaBuilder.dtNascimento();
    this.documentoCpf = usuarioNovaBuilder.documentoCpf();
    this.cidadeNascimento = usuarioNovaBuilder.cidadeNascimento();
  }
  
   public static UsuarioNovoBuilder builder() {
    return new UsuarioNovoBuilder();
  }
  


}