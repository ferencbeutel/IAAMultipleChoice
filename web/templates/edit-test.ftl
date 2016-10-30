<#assign currentPage = "testAdministration">

<html>
<head>
    <title><@s.text name="edit-test.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/edit-test.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="edit-test" method="post">
    <@s.hidden class="form-control form-control-lg" name="seminarId" value="${seminar.seminarId}"/>
    <@s.hidden class="form-control form-control-lg" name="test.testId" value="${seminar.test.testId}"/>

    <div class="form-group row">
        <label for="seminarName"
               class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectSeminar"/> </label>
        <div class="col-md-7">
            <@s.textfield class="form-control form-control-lg" id="seminarName" name="seminarName" value="${seminar.name}" readonly="true"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="selectcredits"
               class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectCredits"/> </label>
        <div class="col-md-7">
            <@s.select list="@de.nordakademie.multiplechoice.model.CreditPointsType@values()" class="select form-control" id="selectcredits" name="test.creditPoints" listValue="realVal" value="seminar.test.creditPoints"/>
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
                       type="text" value="${seminar.test.beginDate}"/>
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
                       type="text" value="${seminar.test.endDate}"/>
            </div>
        </div>
    </div>

    <div class="form-group row">
        <label for="duration"
               class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectDuration"/></label>
        <div class="col-md-7">
            <@s.textfield class="form-control form-control-lg" id="duration" name="durationString" value="${seminar.test.duration}"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="passThreshold"
               class="col-md-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectPassThreshold"/></label>
        <div class="col-md-7">
            <@s.textfield class="form-control form-control-lg" id="passThreshold" name="test.minScore" value="${seminar.test.minScore}"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="selectFalseAnswerGrading"
               class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectFalseAnswerGrading"/> </label>
        <div class="col-md-7">
            <@s.select list="@de.nordakademie.multiplechoice.model.EvaluationType@values()" class="select form-control" id="selectfalseanswergrading" name="test.evaluationType" listValue="realVal" value="seminar.test.evaluationType"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>
    <@s.submit class="btn btn-primary" value="Add"/>
</@s.form>
    <hr/>

<--!ToDo: Liste von den bisher zum Test erstellten Fragen anzeigen-->

<div class="form-group row">
    <label for="addQuestion"
           class="col-md-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="editTest.info"/> </label>

    <button class="btn btn-secondary add-question-button"><@s.text name="editTest.addQuestionButton"/></button>

</div>
</div>


<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/add-test-time-picker.js"></script>
<script type="text/javascript" src="/static/js/editTest.js"></script>
</body>
</html>