<#macro mapeamento modelMap>
<#if modelMap.existeManyToOne>
  @ManyToOne(fetch = FetchType.LAZY<#if modelMap.optionalFalse>, optional = false</#if><#if modelMap.usaCascade>, cascade = CascadeType.${modelMap.cascade}</#if>)<#if modelMap.usaJoinColumn>
  @JoinColumn(name = "cd${modelMap.tipo}"<#if modelMap.optionalFalse>, nullable = false</#if>)</#if>	
</#if>
<#if modelMap.existeOneToOne>
  @OneToOne(fetch = FetchType.LAZY<#if modelMap.optionalFalse>, optional = false</#if><#if modelMap.naoUsaJoinColumn>, mappedBy = "${modelMap.mappedBy}"</#if><#if modelMap.usaCascade>, orphanRemoval = true, cascade = CascadeType.${modelMap.cascade}</#if>)<#if modelMap.usaJoinColumn>
  @JoinColumn(name = "cd${modelMap.tipo}"<#if modelMap.optionalFalse>, nullable = false</#if>)</#if>	
</#if>
<#if modelMap.existeOneToMany>
  @OneToMany(fetch = FetchType.LAZY<#if modelMap.optionalFalse>, optional = false</#if><#if modelMap.naoUsaJoinColumn>, mappedBy = "${modelMap.mappedBy}"</#if><#if modelMap.usaCascade>, orphanRemoval = true, cascade = CascadeType.${modelMap.cascade}</#if>)<#if modelMap.usaJoinColumn>
  @JoinColumn(name = "cd${modelMap.tipo}"<#if modelMap.optionalFalse>, nullable = false</#if>)</#if>	
</#if>
</#macro>