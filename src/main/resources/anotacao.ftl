<#macro anotacao anot>
  ${anot.anotacao}(<#if anot.existeMensagem>message = "${anot.mensagem}"</#if><#if anot.existeComplementoEMensagem>, </#if><#if anot.existeComplemento>${anot.complemento}</#if>)
</#macro>