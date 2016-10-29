<#assign currentPage = "insufficient-permissions-errorpage">

<html>
<head>
    <title><@s.text name="insufficientPermissionsErrorpage.title"/></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <i class="fa fa-exclamation-circle fa-2x" aria-hidden="true"> </i>
        <p><@s.text name="insufficientPermissionsErrorpage.info"/></p>
     </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>