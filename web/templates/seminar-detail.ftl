<#assign currentPage = "seminarList">

<html>
<head>
    <title>Seminar details</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/seminarDetailList.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="seminarList">
    <div class="row seminarListItem">
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.name}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate} - ${seminar.endDate}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.lecturer.user.name} ${seminar.lecturer.user.surName}</span>
        </div>
    </div>
    <div class="row seminarListItem">
        <div class="col-xs-10 seminarListItemEntry centered">
            <span>${seminar.htmlDescription}</span>
        </div>
    </div>
    <div class="row seminarListHeader">
        <span class="col-xs-12 seminarListItemEntry">Participants</span>
    </div>
<#list seminar.participants as participant>
    <div class="row participantsListItem">
        <div class="col-xs-12 centered">
            <span>${participant.user.name} ${participant.user.surName}</span>
        </div>
    </div>
<#else>
    <div class="row participantsListItem">
        <div class="col-xs-12 centered">
            <span>no participants yet</span>
        </div>
    </div>
</#list>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>