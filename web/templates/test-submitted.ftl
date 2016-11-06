<#assign currentPage = "perform-test">

<html>
<head>
    <title>Test Submitted</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <span><@s.text name="testresult.info"/> ${result.points} <@s.text name="testresult.points"/></span>
        </div>
    </div>
</div>
<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/test-submit.js"></script>
</body>
</html>