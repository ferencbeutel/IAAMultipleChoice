<#assign currentPage = "impressum">

<html>
<head>
    <title>Impressum</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <h1> <@s.text name="impressum.headline"/></h1>
        <p><@s.text name="impressum.Info"/></p>
        <p><@s.text name="impressum.school.Info"/></p>
        <p><@s.text name="impressum.street"/></p>
        <p><@s.text name="impressum.city"/></p>
        <p><@s.text name="impressum.phone"/></p>
        <p><@s.text name="impressum.fax"/></p>
        <p><@s.text name="impressum.mail"/></p>
        <p><@s.text name="impressum.web"/></p>


    </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>