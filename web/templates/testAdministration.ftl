<#assign currentPage = "testAdministration">

<html>
<head>
    <title>Test Verwalten</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <p>Hier Sehen sie alle bereits existierenden Tests. Oh Shit. Der Tarnumhang</p>
        <p>Cool oder ?</p>
    </div>
</div>

<p>Wenn Sie einen neuen Test erstellen wollen, klicken Sie bitte <a class="nav-link" href="addTest">hier</a>.</p>
<p>Wenn Sie zu einem Test neue Fragen erstellen wollen, klicken Sie bitte <a class="nav-link" href="addQuestion">hier</a>.</p>
<#include "/templates/frameFooter.ftl">
</body>
</html>