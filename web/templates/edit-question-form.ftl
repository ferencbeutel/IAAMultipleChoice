<#assign currentPage = "testAdministration">

<html>
<head>
    <title><@s.text name="edit-question-form.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/question.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="edit-question" method="post">
    <@s.hidden name="seminarId" value="${seminarId?c}"/>
    <@s.hidden name="questionId" value="${questionId?c}"/>
    <@s.hidden name="question.position" value="${question.position}"/>
    <div class="form-group row"
    <div><@s.text name="editQuestion.info"/></div>
    <hr/>
    <div class="form-group row text-no-center">
        <label for="select-question-type"
               class="col-xs-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.typeOfQuestion"/></label>
        <div class="col-xs-10">
            <@s.select list="@de.nordakademie.multiplechoice.model.QuestionType@values()" id="select-question-type" class="select form-control" name="question.type" listValue="realVal" value="question.type"/>
        </div>
    </div>
    <div class="form-group row text-no-center" >
        <label for="points"
               class="col-xs-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.points"/></label>
        <div class="col-xs-10">
            <@s.textfield class="form-control form-control-lg" name="question.points"/>
        </div>
    </div>
    <div class="row none" id="gapAnswersText">
        <span class="col-xs-12"><@s.text name="addQuestion.gapInfo"/></span>
    </div>
    <div class="form-group row text-no-center">
        <label for="enterQuestion"
               class="col-xs-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.question"/></label>
        <div class="col-xs-10">
            <@s.textarea name="question.text" id="questionText" class="form-control form-control-lg" placeholder="Ihre Frage"/>
        </div>
    </div>
    <div class="text-no-center" id="answerChoices">
        <div class="none answerList" id="singleChoiceAnswers"></div>
        <div class="none answerList" id="multipleChoiceAnswers"></div>
        <div class="none answerList" id="gapAnswers"></div>
    </div>
    <br/>
    <div class="form-group row">
        <div class="col-xs-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-4 centered">
            <button type="button" class="manageAnswersButtons fa fa-plus" id="addAnswerButton"/>
            <button type="button" class="manageAnswersButtons fa fa-minus" id="removeAnswerButton"/>
        </div>
    </div>
    <br/>
    <div class="none" id="hiddenAnswerLabelBaseValue"><@s.text name="addQuestion-form.answer"/></div>
    <#list question.answers as answer>
        <div class="none hiddenAnswer" data-text="${answer.text}" data-value="${answer.correct?c}"></div>
    </#list>
    <button type="submit" class="btn btn-primary"><@s.text name="button.save"/></button>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/init-question-form.js"></script>
<script type="text/javascript" src="/static/js/edit-question.js"></script>
</body>
</html>