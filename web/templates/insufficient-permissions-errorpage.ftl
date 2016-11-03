<#assign currentPage = "insufficient-permissions-errorpage">

<html>
<head>
    <title><@s.text name="insufficientPermissionsErrorpage.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/errorpage.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12 error-title">
        <i class="fa fa-exclamation-circle fa-lg" aria-hidden="true"> </i>
         <@s.text name="global-errorPage.OPS"/>
        <div class="error-text"><@s.text name="insufficientPermissionsErrorpage.info"/></div>
     </div>
    <img src="/static/images/errorpage_paper_permission_required.png" alt="insufficient_permissions" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>