<#assign currentPage = "start-test">

<html>
<head>
    <title>Start test</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="container">
<@s.form action="perform-test" method="post">
    <div class="row">
        <div class="col-xs-12">
            <@s.hidden name="seminarId" value="${seminarId?c}"/>
            <@s.hidden name="savedAccessToken" value="${savedAccessToken}"/>
            <div class="form-group row">
                <label for="accessToken"
                       class="col-md-4 col-form-label col-form-label-lg text-no-center">access-token: </label>
                <div class="col-md-8">
                    <@s.textfield class="form-control form-control-lg" id="accessToken" name="inputAccessToken"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>
    <div class="row">
        <button type="submit" class="btn btn-primary"><@s.text name="button.submit"/></button>
    </div>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>