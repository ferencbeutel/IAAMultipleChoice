<#assign currentPage = "perform-test">

<html>
<head>
    <title><@s.text name="performTest.title"/></title>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/perform-test.css"/>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="container">
    <div class="row">
    <div class="col-xs-12">
    <@s.form id="testSubmitForm" action="submit-test" method="post">
        <@s.hidden name="seminarId" value="${seminarId}"/>
        <@s.hidden name="testResultId" value="${testResultId}"/>
        <div class="row">
            <div id="questionContainer" class="col-xs-6 centered">
                <div class="slickContainer">
                    <#list seminar.test.questions as question>
                        <div class="question">
                            <div class="row">
                                <div class="col-xs-12 questionText">
                                    <span>${question.text}</span>
                                </div>
                            </div>
                            <#list question.answers as answer>
                                <#if question.type == "Gap">
                                    <div class="row">
                                        <div class="col-xs-4"></div>
                                        <div class="col-xs-6 answerText" data-id="${answer.answerId}">
                                            <@s.textfield class="gapAnswerInput" name="gapInput"/>
                                        </div>
                                    </div>
                                <#else>
                                    <div class="row">
                                        <div class="col-xs-10 answerText">
                                            <span>${answer.text}</span>
                                        </div>
                                        <div class="col-xs-2 checkBoxes">
                                            <input type="checkbox" name="singleMultiChoiceInput"
                                                   value="${answer.answerId}"/>
                                        </div>
                                    </div>
                                </#if>
                            </#list>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit Test</button>
    </div>
    </@s.form>
    </div>
</div>
<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.slick/1.6.0/slick.min.js"></script>
<script type="text/javascript" src="/static/js/perform-test.js"></script>
</body>
</html>