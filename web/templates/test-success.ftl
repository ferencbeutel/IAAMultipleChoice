<#assign currentPage = "test">

<html>
<head>
    <title>Hello</title>
    <#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<p>Hello, ${name}</p>

<#include "/templates/frameFooter.ftl">
</body>
</html>