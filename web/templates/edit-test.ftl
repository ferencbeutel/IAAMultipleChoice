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
    <@s.hidden id = "hiddenSeminarId" class="form-control form-control-lg" name="seminarId" value="${seminar.seminarId}"/>
    <@s.hidden class="form-control form-control-lg" name="test.testId" value="${seminar.test.testId}"/>

    <div class="form-group row">
        <label for="seminarName"
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectSeminar"/> </label>
        <div class="col-xs-7">
            <@s.textfield class="form-control form-control-lg" id="seminarName" name="seminarName" value="${seminar.name}" readonly="true"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="selectcredits"
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectCredits"/> </label>
        <div class="col-xs-7">
            <@s.select list="@de.nordakademie.multiplechoice.model.CreditPointsType@values()" class="select form-control" id="selectcredits" name="test.creditPoints" listValue="realVal" value="seminar.test.creditPoints"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="startDate"
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectStartdate"/></label>
        <div class="col-xs-7">
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
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectEnddate"/></label>
        <div class="col-xs-7">
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
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectDuration"/></label>
        <div class="col-xs-7">
            <@s.textfield class="form-control form-control-lg" id="duration" name="durationString" value="${seminar.test.duration}"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="passThreshold"
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"><@s.text name="test.selectPassThreshold"/></label>
        <div class="col-xs-7">
            <@s.textfield class="form-control form-control-lg" id="passThreshold" name="test.minScore" value="${seminar.test.minScore}"/>
        </div>
    </div>

    <div class="form-group row">
        <label for="selectFalseAnswerGrading"
               class="col-xs-5 col-form-label col-form-label-lg text-no-center"> <@s.text name="test.selectFalseAnswerGrading"/> </label>
        <div class="col-xs-7">
            <@s.select list="@de.nordakademie.multiplechoice.model.EvaluationType@values()" class="select form-control" id="selectfalseanswergrading" name="test.evaluationType" listValue="realVal" value="seminar.test.evaluationType"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-xs-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>
    <div class="questionList">
        <div class="row questionListHeader">
            <div class="col-xs-12 questionListHeaderItem">Questions</div>
        </div>
        <#list seminar.test.questions as question>
            <div class="row">
                <div class="col-xs-12">${question.text}</div>
            </div>
        </#list>
        <div class="form-group row">
            <div class="col-xs-8 centered">
                <button type="button" class="btn btn-secondary add-question-button"><@s.text name="editTest.addQuestionButton"/></button>
            </div>
        </div>
    </div>
    <@s.submit class="btn btn-primary" value="Save"/>
</@s.form>
</div>


<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/add-test-time-picker.js"></script>
<script type="text/javascript" src="/static/js/editTest.js"></script>
</body>
</html>