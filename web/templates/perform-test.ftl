<#assign currentPage = "perform-test">

<html>
<head>
    <title><@s.text name="performTest.title"/></title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="container">
    <div class="row">
    <div class="col-xs-12">
    <@s.form action="submit-test" method="post">
        <div class="form-group row">
            <span>${seminar.name}</span>
            <span>${seminar.test.questions?size}</span>
        </div>
        <div class="form-group row">
            <div class="col-md-6 centered" id="validation-errors">
                <@s.fielderror/>
            </div>
        </div>
        <@s.submit class="btn btn-primary" value="Submit Test"/>
    </div>
    </@s.form>
    </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>