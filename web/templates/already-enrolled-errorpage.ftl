<#assign currentPage = "already-enrolled-errorpage">

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
        <p><@s.text name="alreadyEnrolledErrorPage.info"/></p>
    </div>
    <img src="/static/images/errorpage_paper_garfield.png" alt="already_enrolled" />
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>