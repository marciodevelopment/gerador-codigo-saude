package br.org.ici.saude.geradorcodigo.angular.models;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AppRoutingPathAngularModel {
  private final Set<AppRoutingAngularModel> routings;
}
