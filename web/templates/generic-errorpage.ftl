<#assign currentPage = "generic-errorpage">

<html>
<head>
    <title><@s.text name="genericErrorPage.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/errorpage.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12 error-title">
        <i class="fa fa-exclamation-circle fa-lg" aria-hidden="true"> </i>
        <@s.text name="global-errorPage.OPS"/>
        <div class="error-text"><@s.text name="genericErrorPage.info"/> </div>
    </div>
    <img src="/static/images/errorpage_paper_lego.png" alt="generic_error" />
</div>
<#include "/templates/frameFooter.ftl">
</body>
</html>