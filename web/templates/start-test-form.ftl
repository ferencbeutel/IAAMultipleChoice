<#assign currentPage = "start-test">

<html>
<head>
    <title><@s.text name="startTest.title"/></title>
    <link rel="stylesheet" type="text/css" href="/static/css/start-test-form.css"/>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="container">
<@s.form action="perform-test" method="post">
    <div class="row">
        <div class="col-xs-12 text-no-center text-large">
            <p><@s.text name="startTest.accessTokenInfo"/></p>
        </div>
        <div class="col-xs-12">
            <@s.hidden name="seminarId" value="${seminarId?c}"/>
            <@s.hidden name="savedAccessToken" value="${savedAccessToken}"/>
            <div class="form-group row">
                <label for="accessToken"
                       class="col-md-4 col-form-label col-form-label-lg text-no-center"><@s.text name="startTest.accessToken"/></label>
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
        <button type="submit" class="btn btn-primary"><@s.text name="startTest.startTestButton"/></button>
    </div>
</@s.form>
    <div class="row">
        <div class="col-xs-12 text-no-center">
            <div class="text-large">
                <@s.text name="startTest.note"/>
            </div>
            <span><@s.text name="startTest.description"/><br>
            <@s.text name="startTest.postponeButton"/><br>
            <@s.text name="startTest.timeLapse"/><br>
            <@s.text name="startTest.QuestionTypes"/></span>
            <div class="text-margin-left">
                <span><@s.text name="startTest.QuestionTypeSingle"/> <br>
                <p></p><@s.text name="startTest.QuestionTypeMultiple"/></p>
                <p></p><@s.text name="startTest.QuestionTypeGap"/></p></span>
            </div>
        </div>
    </div>

</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>