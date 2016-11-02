<#assign currentPage = "registration">

<html>
<head>
    <title><@s.text name="registration.title"/></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <p><@s.text name="registration.Step1"/></p>
        <p><@s.text name="registration.email"/></p>

    </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>