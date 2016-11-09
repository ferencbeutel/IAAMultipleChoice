<#assign currentPage = "400-errorpage">

<html>
<head>
    <title><@s.text name="errorpage400.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/errorpage.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12 error-title">
        <i class="fa fa-exclamation-circle fa-lg" aria-hidden="true"> </i>
        <@s.text name="global-errorPage.OPS"/>
        <div class="error-text">
           <p><@s.text name="errorpage400.info"/></p>
           <@s.text name="global.homeLink"/> <a class="nav-link" href="home"><@s.text name="global.clickHereLink"/></a>.<br>
        </div>
    </div>
    <img src="/static/images/errorpage_paper_code.png" alt="400_error" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>