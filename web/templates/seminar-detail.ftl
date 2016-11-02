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
    <div class="row seminarListHeader" id="headline">
        <span class="col-xs-12 seminarListItemEntry">${seminar.name}</span>
       </div>
    <div class="row seminarListHeader">
        <span class="col-xs-4 seminarListItemEntry"><@s.text name="seminarDetail.startdate"/></span>
        <span class="col-xs-4 seminarListItemEntry"><@s.text name="seminarDetail.enddate"/></span>
        <span class="col-xs-4 seminarListItemEntry"><@s.text name="seminarDetail.lecturer"/></span>
    </div>
    <div class="row seminarListItem">
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>  ${seminar.endDate}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.lecturer.name} ${seminar.lecturer.surName}</span>
        </div>
    </div>
    <div class="row seminarListItem">
        <div class="col-xs-12 centered">
            <div class="col-xs-2 seminarListItemEntry" id="seminarDescriptionLabel">
                <span><@s.text name="seminarDetail.description"/></span>
            </div>
            <div class="description col-xs-10 seminarListItemEntry" id="seminarDescriptionText">
                <p class="descritpion-text">${seminar.htmlDescription}</p>
            </div>
        </div>
    </div>
    <div class="row seminarListHeader" id="participants">
        <span class="col-xs-12 seminarListItemEntry"><@s.text name="seminarDetail.participants"/></span>
    </div>

    <div class="row seminarListItem">
        <div class="col-xs-12 centered">
            <div class="col-xs-2 seminarListItemEntry">
                <span><@s.text name="seminarDetail.number"/></span>
            </div>
            <div class="col-xs-5 seminarListItemEntry">
                <span><@s.text name="seminarDetail.firstName"/></span>
            </div>
            <div class="col-xs-5 seminarListItemEntry">
                <span><@s.text name="seminarDetail.lastName"/></span>
            </div>
        </div>
    </div>
<#assign enrolled = "false">
<#list seminar.participants as participant>

    <div class="row participantList">
        <div class="col-xs-12 centered">
            <div class="col-xs-2 participantListEntry">
                <span>${participant?index+1}</span>
            </div>
            <div class="col-xs-5 participantListEntry">
                <span>${participant.name}</span>
            </div>
            <div class="col-xs-5 participantListEntry">
                <span>${participant.surName}</span>
            </div>
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
                <button class="btn btn-secondary enrollButton"><@s.text name="seminarDetail.enroll"/></button>
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