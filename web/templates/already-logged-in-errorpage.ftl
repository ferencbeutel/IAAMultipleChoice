<#assign currentPage = "already-logged-in-errorpage">

<html>
<head>
    <title><@s.text name="alreadyLoggedInErrorPage.title"/></title>
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
    <img src="/static/images/errorpage_paper_open_lock.png" alt="already_loggedIn" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>