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
<@s.form action="addQuestion" method="post">

    <div class="form-group row">
        <label for="selectposition" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.position"/></label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" value="1" list="questionPosition" id="selectPosition" name="question.position"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="selectquestiontype"
               class="col-md-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.typeOfQuestion"/></label>
        <div class="col-md-10">
            <@s.select list="questionTypes" class="select form-control" id="selectQuestionType" name="questionTypeString" onchange="changeQuestionType(this)"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="points"
               class="col-md-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.points"/></label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="points" name="question.points"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="enterQuestion"
               class="col-md-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.question"/></label>
        <div class="col-md-10">
            <@s.textarea name="question.text" id="enterQuestion" class="form-control form-control-lg" onblur="calculateGaps()" placeholder="Ihre Frage"/>
        </div>
    </div>
    <div class="container" id="answerChoice">
        <div class="form-group row" id="answerChoice1">
            <label for="answerChoice1Text"
                   class="col-md-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.answer1"/></label>
            <div class="col-md-10">
                <@s.textfield class="form-control form-control-lg answer" id="answerChoice1Text" name="answerTexts"/>
                <#--<input name="answerValidity" type="radio" id="answerChoice1Valid" name="answerValidity"/>
                <@s.radio name="answerValidity" id="answerChoice1Valid" value="true"
            list="{''}"/>-->
                <@s.checkbox id="answerChoice1Valid" label="checkbox test" name="answerValidity" key="kevin" fieldValue="true"/>
                <@s.checkbox id="answerChoice2Valid" label="checkbox test" name="answerValidity" key="ferdinand" fieldValue="true"/>
            </div>
        </div>
    </div>

    <div class="container none" id="answerGap">
        <div class="form-group row" id="answerGap1">
            <label for="answerGap1Text" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="addQuestion-form.answer1"/></label>
            <div class="col-md-10">
                <@s.textfield class="form-control form-control-lg answer" id="answerGap1Text" name="answerGap1Text"/>
                <input type="radio" class="none" id="answerGap1Valid" name="answerValidity2"/>
            <#--
            <select class="gapSelect none" id="answerGap1Valid" name="gapValid">
                <option value="1">1</option>
            </select>
            -->
            </div>
        </div>
    </div>


    <@s.submit class="btn btn-primary" value="Submit"/>
</@s.form>
    <div class="form-group row" id="manageAnswers">
        <button class="manageAnswer" onclick="addAnswer()">+</button>
        <button class="manageAnswer" onclick="removeAnswer()">-</button>
    </div>
    <div class="form-group row">
        <div class="col-md-6 centered" id="validation-errors">
        <@s.fielderror/>
        </div>
    </div>
</div>




<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/addQuestionForm.js"></script>
</body>
</html>