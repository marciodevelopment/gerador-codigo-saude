package br.org.ici.saude.geradorcodigo.entidade;

import java.util.HashSet;
import java.util.Set;
import br.org.ici.saude.geradorcodigo.common.ArquivoType;
import br.org.ici.saude.geradorcodigo.imports.GeradorImports;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapeamentoModel {
  @Getter
  private final String tipoMapeamento;
  private final Boolean joinColumn;
  private final String cascade;
  private final boolean atributoPodeSerNulo;
  private final String tipo;
  @Getter
  private final String mappedBy;

  public Boolean getExisteOneToOne() {
    if (naoExisteMapeamento()) {
      return false;
    }
    return tipoMapeamento.toLowerCase().contains("onetoone");
  }

  public Boolean getExisteOneToMany() {
    if (naoExisteMapeamento()) {
      return false;
    }
    return tipoMapeamento.toLowerCase().contains("onetomany");
  }

  public Boolean getExisteManyToOne() {
    if (naoExisteMapeamento()) {
      return false;
    }
    return tipoMapeamento.toLowerCase().contains("manytoone");
  }


  public Boolean getOptionalFalse() {
    return !atributoPodeSerNulo;
  }

  private boolean naoExisteMapeamento() {
    return tipoMapeamento == null;
  }

  public Boolean getUsaJoinColumn() {
    return joinColumn;
  }


  public Boolean getNaoUsaJoinColumn() {
    return !joinColumn;
  }

  public String getTipo() {
    return this.tipo.replace(ArquivoType.ENTITY.getSufixo(), "");
  }

  public boolean getUsaCascade() {
    return cascade != null && cascade.length() > 0;
  }

  public String getCascade() {
    if (this.getUsaCascade()) {
      return cascade.toUpperCase();
    }
    return "";
  }

  public Set<String> getImports() {
    Set<String> imports = new HashSet<>();
    if (this.getExisteManyToOne().booleanValue())
      imports.add(GeradorImports.getImport("manytoone"));
    if (this.getExisteOneToMany().booleanValue())
      imports.add(GeradorImports.getImport("onetomany"));
    if (this.getExisteOneToOne().booleanValue())
      imports.add(GeradorImports.getImport("onetoone"));
    if (this.getUsaCascade())
      imports.add(GeradorImports.getImport("CascadeType"));
    if (this.getUsaJoinColumn().booleanValue())
      imports.add(GeradorImports.getImport("JoinColumn"));
    imports.add(GeradorImports.getImport("FetchType"));

    return imports;

  }

}
