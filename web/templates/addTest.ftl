<#assign currentPage = "testAdministration">

<html>
<head>
    <title><@s.text name="addTest.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/addTest.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<#if Session?? && Session.userMail??>
<div class="container">

    <@s.form action="addTest" method="post">

        <div class="form-group row">
            <label for="seminar"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="addTest.selectSeminar"/> </label>
            <div class="col-md-7">
                <span>${seminar.name}</span>
            </div>
        </div>

        <div class="form-group row">
            <label for="selectcredits"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="addTest.selectCredits"/> </label>
            <div class="col-md-7">
                <@s.select list="creditPointsTypes" class="select form-control" id="selectcredits" name="creditPointsString"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="startDate"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="addTest.selectStartdate"/></label>
            <div class="col-md-7">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                    </div>
                    <input class="form-control form-control-lg" id="startDate" name="startDate"
                           placeholder="MM/DD/YYYY"
                           type="text"/>
                </div>
            </div>
        </div>


        <div class="form-group row">
            <label for="endDate"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="addTest.selectEnddate"/></label>
            <div class="col-md-7">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                    </div>
                    <input class="form-control form-control-lg" id="endDate" name="endDate" placeholder="MM/DD/YYYY"
                           type="text"/>
                </div>
            </div>
        </div>


        <div class="form-group row">
            <label for="duration"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="addTest.selectDuration"/></label>
            <div class="col-md-7">
                <@s.textfield class="form-control form-control-lg" id="duration" name="duration"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="passThreshold"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="addTest.selectPassThreshold"/></label>
            <div class="col-md-7">
                <@s.textfield class="form-control form-control-lg" id="passThreshold" name="test.minScore"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="selectFalseAnswerGrading"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="addTest.selectFalseAnswerGrading"/> </label>
            <div class="col-md-7">
                <@s.select list="evaluationTypes" class="select form-control" id="selectfalseanswergrading" name="evaluationTypeString"/>
                    <#--            <select class="select form-control" id="selectfalseanswergrading" name="test.evaluationType">
                <option value="SUBSTRACT"> <@s.text name="addTest.selectFalseAnswerGrading.ZeroPointDeduction"/></option>
                <option value="IGNORE"> <@s.text name="addTest.selectFalseAnswerGrading.OnePointDeduction"/></option>
            </select>-->
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6 centered" id="validation-errors">
                <@s.fielderror/>
            </div>
        </div>
        <@s.submit class="btn btn-primary" value="Add"/>
    </@s.form>
</div>

<#else>
<p><@s.text name="global.notLoggedIn.info"/></p>
</#if>

<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/addTestTimePicker.js"></script>
</body>
</html>