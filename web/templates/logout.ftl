<#assign currentPage = "logout">

<html>
<head>
    <title><@s.text name="logout.title"/></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <i class="fa fa-check" aria-hidden="true"> </i>
        <@s.text name="logout.info"/>
        <p><@s.text name="logout.seeyou"/></p>
    </div>
    <img src="/static/images/logoutpage_door_locked.png" alt="logout" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>