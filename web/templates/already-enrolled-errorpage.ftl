<#assign currentPage = "already-enrolled-errorpage">

<html>
<head>
    <title><@s.text name="alreadyLoggedInErrorPage.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/errorpage.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12 error-title">
        <i class="fa fa-exclamation-circle fa-lg" aria-hidden="true"> </i>
        <@s.text name="global-errorPage.OPS"/>
        <div class="error-text"> <@s.text name="alreadyEnrolledErrorPage.info"/></div>
    </div>
    <img src="/static/images/errorpage_paper_garfield.png" alt="already_enrolled" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>