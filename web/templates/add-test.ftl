<#assign currentPage = "testAdministration">

<html>
<head>
    <title><@s.text name="add-test.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/add-test.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">

    <@s.form action="add-test" method="post">

        <div class="form-group row">
            <label for="seminarName"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectSeminar"/> </label>
            <div class="col-md-7">
                <@s.textfield class="form-control form-control-lg" id="seminarName" name="seminarName" value="${seminar.name}" readonly="true"/>
                <@s.hidden id="hiddenSeminarId" name="seminarId" value="${seminar.seminarId?c}"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="selectcredits"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectCredits"/> </label>
            <div class="col-md-7">
                <@s.select list="@de.nordakademie.multiplechoice.model.CreditPointsType@values()" class="select form-control" id="selectcredits" name="test.creditPoints" listValue="realVal"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="startDate"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectStartdate"/></label>
            <div class="col-md-7">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                    </div>
                    <input class="form-control form-control-lg" id="startDate" name="startDateString"
                           placeholder="MM/DD/YYYY"
                           type="text"/>
                </div>
            </div>
        </div>


        <div class="form-group row">
            <label for="endDate"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectEnddate"/></label>
            <div class="col-md-7">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                    </div>
                    <input class="form-control form-control-lg" id="endDate" name="endDateString" placeholder="MM/DD/YYYY"
                           type="text"/>
                </div>
            </div>
        </div>


        <div class="form-group row">
            <label for="duration"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectDuration"/></label>
            <div class="col-md-7">
                <@s.textfield placeholder="HH:mm" class="form-control form-control-lg" id="duration" name="durationString"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="passThreshold"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectPassThreshold"/></label>
            <div class="col-md-7">
                <@s.textfield class="form-control form-control-lg" id="passThreshold" name="test.passingThreshold"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="selectFalseAnswerGrading"
                   class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectFalseAnswerGrading"/> </label>
            <div class="col-md-7">
                <@s.select list="@de.nordakademie.multiplechoice.model.EvaluationType@values()" class="select form-control" id="selectfalseanswergrading" name="test.evaluationType" listValue="realVal"/>
                    <#--            <select class="select form-control" id="selectfalseanswergrading" name="test.evaluationType">
                <option value="SUBSTRACT"> <@s.text name="add-test.selectFalseAnswerGrading.ZeroPointDeduction"/></option>
                <option value="FATAL"> <@s.text name="add-test.selectFalseAnswerGrading.OnePointDeduction"/></option>
            </select>-->
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6 centered" id="validation-errors">
                <@s.fielderror/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><@s.text name="button.submit"/></button>
    </@s.form>
</div>

<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/add-test-time-picker.js"></script>
</body>
</html>