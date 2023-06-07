package br.org.ici.saude.usuario.entity.type;

import br.org.ici.saude.usuario.common.BaseEnum;
import br.org.ici.saude.usuario.common.EnumUtil;
import lombok.Getter;

@Getter
public enum SexoType implements BaseEnum {
   MASCULINO(1, "Masculino"),
   FEMININO(2, "Feminino");

  private Integer id;
  private String description;

  SexoType(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public static SexoType toType(Integer id) {
    return EnumUtil.toType(id, SexoType.class);
  }
}
