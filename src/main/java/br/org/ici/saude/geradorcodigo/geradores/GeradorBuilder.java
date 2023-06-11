package br.org.ici.saude.geradorcodigo.geradores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import br.org.ici.saude.geradorcodigo.common.ArquivoModel;
import br.org.ici.saude.geradorcodigo.configuracao.ArquivoConfiguracao;
import br.org.ici.saude.geradorcodigo.configuracao.EntidadeArquivo;
import br.org.ici.saude.geradorcodigo.entidade.EntidadeModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeradorBuilder implements GeradorArquivo {


  public List<? extends ArquivoModel> converterParaArquivoModel(
      ArquivoConfiguracao arquivoConfiguracao) {
    List<EntidadeModel> entidades = arquivoConfiguracao.getEntidades().stream()
        .filter(ent -> ent.toEntidadeModel().getExisteBuilderConstrutor().booleanValue())
        .map(EntidadeArquivo::toEntidadeModel).toList();

    for (EntidadeModel entidade : entidades) {
      Set<String> imports = entidade.getImports().stream().filter(imp -> !naoExisteEm(imp))
          .collect(Collectors.toSet());
      entidade.setImports(imports);
    }

    return entidades;
  }

  private boolean naoExisteEm(String imp) {
    Map<String, String> imports = new HashMap<>();
    imports.put("lombok.Getter", "lombok.Getter");
    imports.put("jakarta.persistence.JoinColumn", "");
    imports.put("jakarta.persistence.CascadeType", "");
    imports.put("jakarta.persistence.OneToOne", "");
    imports.put("br.org.ici.saude.usuario.entity.type.SexoConverter", "");
    imports.put("jakarta.persistence.Convert", "");
    imports.put("jakarta.validation.constraints.Min", "");
    imports.put("br.org.ici.saude.usuario.entity.type.SituacaoConverter", "");
    imports.put("jakarta.persistence.FetchType", "");
    return imports.containsKey(imp.replace("import", "").replace(";", ""));
  }



}
