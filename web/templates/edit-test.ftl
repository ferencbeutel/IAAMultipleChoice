<#assign currentPage = "testAdministration">

<html>
<head>
    <title><@s.text name="edit-test.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/edit-test.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="edit-test" method="post">
    <@s.hidden id = "hiddenSeminarId" name="seminarId" value="${seminar.seminarId?c}"/>
    <@s.hidden name="test.testId" value="${seminar.test.testId?c}"/>

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
            <@s.textfield class="form-control form-control-lg" id="passThreshold" name="test.passingThreshold" value="${seminar.test.passingThreshold}"/>
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
    <button type="submit" class="btn btn-primary"><@s.text name="button.save"/></button>
</@s.form>
</div>
<hr/>

<div id="questionList" class="questionList">
<#if seminar.test.questions?has_content>
<div class="row questionListItem questionListHeader">
        <div class="col-xs-3 questionListItemEntry"><@s.text name="editTest.TableHeaderPos"/></div>
        <div class="col-xs-4 questionListItemEntry"><@s.text name="editTest.TableHeaderText"/></div>
        <div class="col-xs-5 questionListItemEntry"><@s.text name="editTest.TableHeaderEdit"/></div>
    </div>
    <#list seminar.test.questions as question>
        <div class="row questionListItem" data-id="${question.questionId?c}">

            <div class="col-xs-3 questionListItemEntry">
             <span>${question.position+1}</span></div>
            <div class="col-xs-4 questionListItemEntry">
            <span> ${question.text}</span>
            </div>
            <div id="buttonItemEntry" class="col-xs-5 questionListItemEntry ">
                <button class="btn btn-secondary edit-question-button fa fa-pencil"/>
                <button id="deleteButton" class="delete-question-button btn btn-secondary fa fa-trash"/>
            </div>
        </div>
    </#list>
    <#else>
        <#if (seminar.test.beginDate?datetime("yyyy-MM-dd")?date <= .now?date)>
            <p><@s.text name="editTest.startedEmpty"/></p>
        <#else>
            <p><@s.text name="editTest.emptyQuestionList"/></p>
        </#if>
</#if>
</div>
<hr/>
<div class="form-group row">
    <div class="col-xs-8 centered">
        <#if (seminar.test.beginDate?datetime("yyyy-MM-dd")?date <= .now?date)>
            <#assign toolTip><@s.text name="editTest.tooltip"/></#assign>
            <button type="button" id="addQuestionDisabled"
                    class="btn btn-secondary disabled" data-toggle="tooltip" data-title="${toolTip}" disabled><@s.text name="editTest.addQuestionButton"/></button>
        <#else>
            <button type="button"
                    class="btn btn-secondary add-question-button"><@s.text name="editTest.addQuestionButton"/></button>
        </#if>
    </div>
</div>
<hr/>





<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/add-test-time-picker.js"></script>
<script type="text/javascript" src="/static/js/edit-test.js"></script>
</body>
</html>