<#assign currentPage = "test-already-started-errorpage">

<html>
<head>
    <title><p><@s.text name="testAlreadyStartedErrorPage.title"/></p></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <i class="fa fa-exclamation-circle fa-2x" aria-hidden="true"> </i>
        <p><@s.text name="testAlreadyStartedErrorPage.info"/></p>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>