<#assign currentPage = "not-logged-in-errorpage">

<html>
<head>
    <title><@s.text name="notLoggedInErrorPage.title"/></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <i class="fa fa-exclamation-circle fa-2x" aria-hidden="true"> </i>
        <p><@s.text name="notLoggedInErrorPage.info"/></p>
        <p><@s.text name="global.notRegisteredYet"/> <a class="nav-link" href="registration-form"><@s.text name="global.clickHereLink"/></a>.</p>
        <p><@s.text name="global.alreadyRegistered"/> <a class="nav-link" href="login-form"><@s.text name="global.clickHereLink"/></a>.</p>
    </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>