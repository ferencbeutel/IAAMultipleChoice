<#assign currentPage = "testAdministration">

<html>
<head>
    <title><@s.text name="addQuestion-form.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/question.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="add-question" method="post">
    <@s.hidden name="seminarId" value="${seminarId}"/>
    <div class="form-group row"
         <div><@s.text name="addQuestion.info"/></div>
    <hr/>
    <div class="row" >

    <div class="col-xs-8 none" id="infoTextSingle">
        <@s.text name="addQuestion.infoTextSingle"/></div>
    <div class="col-xs-8 none" id="infoTextMultiple">
        <@s.text name="addQuestion.infoTextMultiple"/></div>
    <div class="col-xs-8 none" id="infoTextGap">
            <@s.text name="addQuestion.infoTextGap"/></div>
        <br>
    </div>
    <div class="form-group row text-no-center">
        <label for="select-question-type"
               class="col-xs-2 col-form-label col-form-label-lg "><@s.text name="addQuestion-form.typeOfQuestion"/></label>
        <div class="col-xs-10">
            <@s.select list="@de.nordakademie.multiplechoice.model.QuestionType@values()" id="select-question-type" class="select form-control" name="question.type" listValue="realVal" headerKey="-1" headerValue=""/>
        </div>
    </div>
    <div class="form-group row text-no-center">
        <label for="points"
               class="col-xs-2 col-form-label col-form-label-lg "><@s.text name="addQuestion-form.points"/></label>
        <div class="col-xs-10">
            <@s.textfield class="form-control form-control-lg" name="question.points"/>
        </div>
    </div>

    <div class="row none " id="gapAnswersText">
        <span class="col-xs-10"><@s.text name="addQuestion.gapInfo"/></span>
    </div>
    <div class="form-group row text-no-center">
        <label for="enterQuestion"
               class="col-xs-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.question"/></label>
        <div class="col-xs-10">
            <@s.textarea name="question.text" id="questionText" class="form-control form-control-lg" placeholder="Ihre Frage"/>
        </div>
    </div>
    <div class=" text-no-center " id="answerChoices">
        <div class="none answerList" id="singleChoiceAnswers">
            <div class="row answerListItem" id="singleChoiceAnswerBaseItem">
                <label for="singleChoiceAnswer-0"
                       class="col-xs-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.answer"/>
                    1</label>
                <div class="col-xs-8">
                    <@s.textfield class="form-control form-control-lg" id="singleChoiceAnswer-0" name="singleChoiceAnswers"/>
                </div>
                <div class="col-xs-2">
                    <input type="checkbox" class="singleChoiceCheckBox" name="singleChoiceAnswerValues" value="0">
                </div>
            </div>
        </div>
        <div class="none answerList" id="multipleChoiceAnswers">
            <div class="row answerListItem" id="multipleChoiceAnswerBaseItem">
                <label for="multipleChoiceAnswer-0"
                       class="col-xs-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.answer"/>
                    1</label>
                <div class="col-xs-8">
                    <@s.textfield class="form-control form-control-lg" id="multipleChoiceAnswer-0" name="multipleChoiceAnswers"/>
                </div>
                <div class="col-xs-2">
                    <input type="checkbox" class="multipleChoiceCheckBox" name="multipleChoiceAnswerValues" value="0">
                </div>
            </div>
        </div>
        <div class="none answerList" id="gapAnswers">
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-4 centered">
            <button type="button" class="manageAnswersButtons" id="addAnswerButton">+</button>
            <button type="button" class="manageAnswersButtons" id="removeAnswerButton">-</button>
        </div>
    </div>
    <br/>
    <div class="none" id="hiddenAnswerLabelBaseValue"><@s.text name="addQuestion-form.answer"/></div>
    <@s.submit class="btn btn-primary" value="Submit"/>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/init-question-form.js"></script>
</body>
</html>