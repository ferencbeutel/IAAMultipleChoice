<#assign currentPage = "logout">

<html>
<head>
    <title>Logout</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <p><@s.text name="logout.info"/></p>
        <p><@s.text name="logout.seeyou"/></p>
    </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>