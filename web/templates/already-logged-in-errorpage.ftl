<#assign currentPage = "already-logged-in-errorpage">

<html>
<head>
    <title><p><@s.text name="alreadyLoggedInErrorPage.title"/></p></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <i class="fa fa-exclamation-circle fa-2x" aria-hidden="true"> </i>
        <p><@s.text name="alreadyLoggedInErrorPage.info"/></p>
        <p><@s.text name="alreadyLoggedInErrorPage.linkToHomeText"/> <a class="nav-link" href="home"><@s.text name="global.clickHereLink"/></a>.</p>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>