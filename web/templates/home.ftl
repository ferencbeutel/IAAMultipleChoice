<#assign currentPage = "home">

<html>
<head>
    <title>Registration</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
    <#if Session?? && Session.user??>
        <p><@s.text name="home.loggedIn.welcome"/>, ${name}</p>
        <p><@s.text name="home.loggedIn.info"/></p>
    <#else>
        <p><@s.text name="home.notLoggedIn.info"/></p>
    </#if>
        <a class="nav-link" href="performTest">hier</a>
    </div>
</div>
<#list seminarList as seminar>
<div class="row">
    <div class="col-xs-4">
        <p>${seminar.name}</p>
    </div>
</div>
</#list>

<#include "/templates/frameFooter.ftl">
</body>
</html>