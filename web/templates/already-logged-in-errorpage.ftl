<#assign currentPage = "already-logged-in-errorpage">

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
        <div class="error-text">
            <@s.text name="alreadyLoggedInErrorPage.info"/>
        <p><@s.text name="alreadyLoggedInErrorPage.linkToHomeText"/> <a class="nav-link" href="home"><@s.text name="global.clickHereLink"/></a>.</p>
        </div>
    </div>
    <img src="/static/images/errorpage_paper_open_lock.png" alt="already_loggedIn" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>