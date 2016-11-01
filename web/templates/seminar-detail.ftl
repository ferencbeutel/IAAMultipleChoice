<#assign currentPage = "seminarList">
<html>
<head>
    <title><@s.text name="seminarDetail.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/seminarDetail.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="seminarList">
    <div class="row seminarListHeader">
        <span class="col-xs-4 seminarListItemEntry"><@s.text name="seminarDetail.name"/></span>
        <span class="col-xs-4 seminarListItemEntry"><@s.text name="seminarDetail.period"/></span>
        <span class="col-xs-4 seminarListItemEntry"><@s.text name="seminarDetail.lecturer"/></span>
    </div>
    <div class="row seminarListItem">
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.name}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate} - ${seminar.endDate}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.lecturer.name} ${seminar.lecturer.surName}</span>
        </div>
    </div>
    <div class="row seminarListHeader">
        <span class="col-xs-12 seminarListItemEntry"><@s.text name="seminarDetail.description"/></span>
    </div>
    <div class="row seminarListItem">
        <div class="col-xs-10 seminarListItemEntry centered">
            <span>${seminar.htmlDescription}</span>
        </div>
    </div>
    <div class="row seminarListHeader">
        <span class="col-xs-12 seminarListItemEntry"><@s.text name="seminarDetail.participants"/></span>
    </div>
<#list seminar.participants as participant>
    <#assign enrolled = "false">
    <div class="row seminarListItem">
        <div class="col-xs-12 centered">
            <span>${participant.name} ${participant.surName}</span>
            <#if participant.email == userMail>
                <#assign enrolled = "true">
            </#if>
        </div>
    </div>
<#else>
    <div class="row seminarListItem">
        <div class="col-xs-12 centered">
            <span><@s.text name="seminarDetail.noParticipants"/></span>
        </div>
    </div>
</#list>
    <div class="row seminarListItem">
        <div class="col-xs-6 centered seminarListItemEntry">
            <#if enrolled=="true">
                <span><@s.text name="seminarDetail.enrolled"/></span>
            <#elseif (seminar.maxParticipants >seminar.participants?size)>
                <button class="btn btn-secondary enrollButton">enroll</button>
            <#else>
                <span><@s.text name="seminarDetail.seminarFull"/></span>
            </#if>
        </div>
    </div>
</div>
<input type="hidden" id="hiddenSeminarId" value="${seminarId}">
<script type="text/javascript" src="/static/js/seminar-detail-page.js"></script>
<#include "/templates/frameFooter.ftl">
</body>
</html>