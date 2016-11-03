<#assign currentPage = "not-logged-in-errorpage">

<html>
<head>
    <title><@s.text name="notLoggedInErrorPage.title"/></title>
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
            <@s.text name="notLoggedInErrorPage.info"/>
            <p><@s.text name="global.notRegisteredYet"/> <a class="nav-link" href="registration-form"><@s.text name="global.clickHereLink"/></a>.</p>
            <p><@s.text name="global.alreadyRegistered"/> <a class="nav-link" href="login-form"><@s.text name="global.clickHereLink"/></a>.</p>
        </div>
    </div>
    <img src="/static/images/errorpage_paper_login.png" alt="not_logged_in" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>